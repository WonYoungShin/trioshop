package com.trioshop.service.user;

import com.trioshop.model.dto.user.KakaoDTO;
import com.trioshop.model.dto.user.Oauth2JoinModel;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.model.dto.user.UsersInfoEntity;
import com.trioshop.repository.dao.user.UserJoinDao;
import com.trioshop.repository.dao.user.UserLoginDao;
import com.trioshop.utils.handler.LoginSuccessHandler;
import com.trioshop.utils.service.JwtTokenUtil;
import jakarta.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class OAuth2LoginService {
    private final UserJoinDao userJoinDao;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserLoginDao userLoginDao;
    private final RestTemplate restTemplate;

    @Autowired
    public OAuth2LoginService(UserJoinDao userJoinDao, JwtTokenUtil jwtTokenUtil, UserLoginDao userLoginDao, RestTemplate restTemplate) {
        this.userJoinDao = userJoinDao;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userLoginDao = userLoginDao;
        this.restTemplate = restTemplate;
    }

    @Value("${kakao.js.key}")
    private String KAKAO_CLIENT_ID;

    @Value("${kakao.restapi.key}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${kakao.request.uri}")
    private String KAKAO_REDIRECT_URL;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
    private final static String KAKAO_API_URI = "https://kapi.kakao.com";

    public String getKakaoLogin() {
        return KAKAO_AUTH_URI + "/oauth/authorize"
                + "?client_id=" + KAKAO_CLIENT_ID
                + "&redirect_uri=" + KAKAO_REDIRECT_URL
                + "&response_type=code";
    }

    public void getKakaoInfo(String code, HttpServletResponse cookieResponse) throws IOException {
        if (code == null) {
            throw new RuntimeException("Failed to get authorization code");
        }

        String accessToken;
        String refreshToken;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", KAKAO_CLIENT_ID);
            params.add("client_secret", KAKAO_CLIENT_SECRET);
            params.add("code", code);
            params.add("redirect_uri", KAKAO_REDIRECT_URL);

            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_AUTH_URI + "/oauth/token",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

            accessToken = (String) jsonObj.get("access_token");
            refreshToken = (String) jsonObj.get("refresh_token");
        } catch (Exception e) {
            throw new RuntimeException("Failed to obtain access token from Kakao API", e);
        }

        KakaoDTO userInfoWithToken = getUserInfoWithToken(accessToken, refreshToken);
        UserInfoBySession userInfoBySession = userLoginDao.loadUserByUsername(userInfoWithToken.getEmail());
        if (userInfoBySession == null) {
            saveOAuthInfo(userInfoWithToken);
            userInfoBySession = userLoginDao.loadUserByUsername(userInfoWithToken.getEmail());
        }

        oauthUserJwtToken(cookieResponse, userInfoBySession);
    }

    private KakaoDTO getUserInfoWithToken(String accessToken, String refreshToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_API_URI + "/v2/user/me",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());
            JSONObject account = (JSONObject) jsonObj.get("kakao_account");
            JSONObject profile = (JSONObject) account.get("profile");

            String email = String.valueOf(account.get("email"));
            String nickname = String.valueOf(profile.get("nickname"));

            return KakaoDTO.builder()
                    .email(email)
                    .nickname(nickname).build();
        } catch (Exception e) {
            if (e.getMessage().contains("access token expired")) {
                return refreshAccessTokenAndRetry(refreshToken);
            }
            throw new RuntimeException("Failed to get user info from Kakao API", e);
        }
    }

    private KakaoDTO refreshAccessTokenAndRetry(String refreshToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "refresh_token");
            params.add("client_id", KAKAO_CLIENT_ID);
            params.add("refresh_token", refreshToken);
            params.add("client_secret", KAKAO_CLIENT_SECRET);

            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_AUTH_URI + "/oauth/token",
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

            String newAccessToken = (String) jsonObj.get("access_token");

            return getUserInfoWithToken(newAccessToken, refreshToken);
        } catch (Exception e) {
            throw new RuntimeException("Failed to refresh access token", e);
        }
    }

    private void oauthUserJwtToken(HttpServletResponse response, UserInfoBySession oauthUser) throws IOException {
        LoginSuccessHandler login = new LoginSuccessHandler(jwtTokenUtil);
        login.loginSuccess(response, oauthUser);
    }

    private void saveOAuthInfo(KakaoDTO kakaoDTO) {
        Long userCode = userJoinDao.insertUsersData(new Oauth2JoinModel(kakaoDTO.getEmail(), "1"));
        userJoinDao.insertUsersInfoData(new UsersInfoEntity(userCode,
                kakaoDTO.getNickname(),
                "010-0000-0000",
                kakaoDTO.getNickname()
        ));
    }
}
