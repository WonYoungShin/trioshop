package com.trioshop.utils.handler;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.utils.service.JwtTokenUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.net.URLEncoder;

import static com.trioshop.JWTConst.*;
import static java.nio.charset.StandardCharsets.*;

/**
 * 로그인 성공시 호출 되는 클래스(핸들러)
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenUtil jwtTokenUtil;

    public LoginSuccessHandler(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //Jwt 토큰 발급 시작
        UserInfoBySession user = (UserInfoBySession) authentication.getPrincipal();
        loginSuccess(response, user);

        if (user.getGradeCode()==4) {
            response.sendRedirect("/trioAdmin");
        } else {
            response.sendRedirect("/");
        }
    }

    public void loginSuccess(HttpServletResponse response, UserInfoBySession user) {
        String accessToken = jwtTokenUtil.generateToken(user);
        String refreshToken= jwtTokenUtil.generateRefreshToken(user);

        String encodedAccessToken = URLEncoder.encode(ACCESS_TOKEN_START1 + accessToken, UTF_8);
        String encodedRefreshToken = URLEncoder.encode(refreshToken, UTF_8);
        Cookie jwtCookie = new Cookie(ACCESS_TOKEN, encodedAccessToken);
        jwtCookie.setHttpOnly(true); // 보안을 위해 HttpOnly 플래그 설정
        // jwtCookie.setSecure(true); // 애플리케이션이 HTTPS를 사용하는 경우 Secure 플래그 설정
        jwtCookie.setPath("/"); // 쿠키의 유효 경로 설정
        jwtCookie.setMaxAge(60 * 5); // 쿠키의 만료 시간 설정 (예: 5분)

        Cookie jwtRefreshCookie = new Cookie(REFRESH_TOKEN, encodedRefreshToken);
        jwtRefreshCookie.setHttpOnly(true); // 보안을 위해 HttpOnly 플래그 설정
        // jwtRefreshCookie.setSecure(true); // 애플리케이션이 HTTPS를 사용하는 경우 Secure 플래그 설정
        jwtRefreshCookie.setPath("/"); // 쿠키의 유효 경로 설정
        jwtRefreshCookie.setMaxAge(60 * 60 * 24 * 7); ///7일

        response.addCookie(jwtCookie);
        response.addCookie(jwtRefreshCookie);
    }
}


//        쿠키를 헤더에 저장하는 방법
//        response.addHeader("Authorization", "Bearer " + token);
        //끝

        //세션 + 쿠키 방식 이전 session 에서 꺼내던 객체들의 호환을 위해 남겨둠
//        HttpSession session = request.getSession();
//        user = UserInfoBySession.builder()
//                .userCode(user.getUserCode())
//                .gradeCode(user.getGradeCode())
//                .userId(user.getUserId())
//                .userNickname(user.getUserNickname())
//                .userPasswd("")
//                .role(user.getRole())
//                .build();
//
//        session.setAttribute(SessionConst.LOGIN_MEMBER,user);
//


/**
 * JWT에서 정보 꺼내오기 사용법 (SampleCode)
 * 현재 인증된 사용자 정보 가져오기
 * Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 * if(authentication != null && authentication.getPrincipal() instanceof UserInfoBySession) {
 *         UserInfoBySession userInfo = (UserInfoBySession) authentication.getPrincipal();
 *         return userInfo();
 *  }
 *   return null
 *
 *   현재 utils.service.SecurityUtils 에 빼놧음
 */
//    세션+ 쿠키 방식
//        @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        HttpSession session = request.getSession();
//        UserInfoBySession user =(UserInfoBySession) authentication.getPrincipal();
//        user = UserInfoBySession.builder()
//                .userCode(user.getUserCode())
//                .gradeCode(user.getGradeCode())
//                .userId(user.getUserId())
//                .userNickname(user.getUserNickname())
//                .userPasswd("")
//                .role(user.getRole())
//                .build();
//
//        session.setAttribute(SessionConst.LOGIN_MEMBER,user);
//        if(user.getRole().equals(Role.ADMIN)){
//            response.sendRedirect("/trioAdmin");
//        }else{
//            response.sendRedirect("/");
//        }
//    }

