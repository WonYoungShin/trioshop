package com.trioshop.utils.service;

import com.trioshop.JWTConst;
import com.trioshop.model.dto.user.UserInfoBySession;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.pl.NIP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.trioshop.JWTConst.*;

@Component
@Slf4j
public class JwtTokenUtil {
    //JWT 비밀키 (예를 들면,  솔트값 같은 느낌)
    @Value("${jwt.secret}")
    private String secretKey;

    private static final long EXPIRATION_TIME = 1800; //1일
//  만료시간 설정                             60 * 60 * 24; 1일

    //사용자 정보를 기반으로 JWT를 생성해주는 메서드
    public String generateToken(UserInfoBySession user) {
        //JWT에 저장할 정보 추가부분
        String userId = user.getUserId();
        Map<String, Object> claims = new HashMap<>();

        claims.put(ROLE, user.getRole());
        claims.put(GRADE_CODE, user.getGradeCode());
        claims.put(USER_NICKNAME, user.getUserNickname());


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId) //토큰 사용 주체
                .setIssuedAt(new Date()) //토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //만료시간
                .signWith(SignatureAlgorithm.HS512, secretKey) //비밀키 + 서명 알고리즘(HS512)를 이용해 암호화
                .compact();
    }


    //토큰에서 사용자 이름 추출
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build() //파서 빌드
                .parseClaimsJws(token) //토큰 파싱
                .getBody();
        return claims.getSubject();
    }

    //토큰에서 사용자 닉네임 추출
    public String getUserNickname(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build() //파서 빌드
                .parseClaimsJws(token) //토큰 파싱
                .getBody();
        return claims.get(USER_NICKNAME, String.class);
    }

    //토큰에서 사용자 그레이드코드 추출
    public Long getGradeCode(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build() //파서 빌드
                .parseClaimsJws(token) //토큰 파싱
                .getBody();
        return claims.get(GRADE_CODE, Long.class);
    }

    // 토큰에서 사용자 권한 추출
    public Role getRoles(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Role.valueOf(claims.get(ROLE, String.class));
    }

    // JWT 토큰 검증 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

}
