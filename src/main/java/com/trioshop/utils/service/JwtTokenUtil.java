package com.trioshop.utils.service;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.repository.redis.RedisRepository;
import com.trioshop.service.user.UserLoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.trioshop.JWTConst.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final RedisRepository redisRepository;
    private final UserLoginService userLoginService;
    //JWT 비밀키 (예를 들면,  솔트값 같은 느낌)
    @Value("${jwt.secret}")
    private String secretKey;

    private static final long ACCESS_EXPIRATION_TIME = 1000*60*5; //5분
    private static final long REFRESH_EXPIRATION_TIME = 1000*60*60*24*7; //7일
//  만료시간 설정                            1000 * 60 * 60 * 24; 1일

    //사용자 정보를 기반으로 JWT를 생성해주는 메서드
    public String generateToken(UserInfoBySession user) {
        //JWT에 저장할 정보 추가부분
        String userId = user.getUserId();
        Map<String, Object> claims = new HashMap<>();

        claims.put(ROLE, user.getRole());
        claims.put(GRADE_CODE, user.getGradeCode());
        claims.put(USER_NICKNAME, user.getUserNickname());
        claims.put(USER_CODE, user.getUserCode());


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId) //토큰 사용 주체
                .setIssuedAt(new Date()) //토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME)) //만료시간
                .signWith(SignatureAlgorithm.HS512, secretKey) //비밀키 + 서명 알고리즘(HS512)를 이용해 암호화
                .compact();
    }


    public String generateRefreshToken(UserInfoBySession user) {
        String userId = user.getUserId();

        String refreshToken = Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        redisRepository.save(userId,refreshToken);

        return refreshToken;
    }

    public String refreshToken(String refreshToken) {
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();

            String userId = claims.getSubject();

            String storedRefreshToken = redisRepository.findById(userId);

            if(refreshToken.equals(storedRefreshToken)){
                UserInfoBySession user = userLoginService.loadUserByUsername(userId);
                return generateToken(user);
            } else{
                throw new RuntimeException("Invalid refresh token");
            }
        }catch (Exception e){
            throw new RuntimeException("Invalid refresh token");
        }
    }

    public void logout(String userId, String token) {
        if (userId != null) {
            redisRepository.deleteToken(userId);
        }
        if (token != null) {
            long expirationTime = getExpirationDateFromToken(token).getTime() - System.currentTimeMillis();
            if (expirationTime > 0) {
                redisRepository.addTokenToBlacklist(token, expirationTime);
            }
        }
    }

    //토큰에서 만료시간 추출
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    //토큰에서 사용자 이름 추출
    public String getUsername(String token){
        return getClaimsFromToken(token).getSubject();
    }

    //토큰에서 사용자 닉네임 추출
    public String getUserNickname(String token){
        return getClaimsFromToken(token).get(USER_NICKNAME, String.class);
    }

    //토큰에서 사용자 그레이드코드 추출
    public Long getGradeCode(String token){
        return getClaimsFromToken(token).get(GRADE_CODE, Long.class);
    }

    // 토큰에서 사용자 권한 추출
    public Role getRoles(String token) {
        return getClaimsFromToken(token).get(ROLE, Role.class);
    }
    //토큰에서 유저 코드 추출
    public Long getUserCode(String token){
        return getClaimsFromToken(token).get(USER_CODE, Long.class);
    }
    // 토큰에서 클레임 추출
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT 토큰 검증 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            String username = getUsername(token);
            String storedToken = redisRepository.findById(username);
            return token.equals(storedToken);
        } catch (ExpiredJwtException e) {
            log.error("Expired Refresh JWT token: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Invalid Refresh JWT token: {}", e.getMessage());
            return false;
        }
    }

}
