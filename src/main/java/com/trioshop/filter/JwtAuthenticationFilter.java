package com.trioshop.filter;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.HeaderModel;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.repository.redis.RedisRepository;
import com.trioshop.service.user.UserLoginService;
import com.trioshop.utils.service.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserLoginService userLoginService;
    private final RedisRepository redisRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 요청 쿠키에서 JWT 토큰 추출
        String token = null;
        String username = null;
        String nickname = null;
        Long gradeCode = null;
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    String header = cookie.getValue();

                    if (header != null && (header.startsWith("Bearer ") || header.startsWith("Bearer+"))) {
                        token = header.substring(7);
                        try {
                            username = jwtTokenUtil.getUsername(token); // 토큰에서 사용자 이름 추출
                            if(jwtTokenUtil.validateToken(token) && !redisRepository.isTokenBlacklisted(token)) {
                                gradeCode = jwtTokenUtil.getGradeCode(token); // 토큰에서 사용자 등급 추출
                                nickname = jwtTokenUtil.getUserNickname(token); // 토큰에서 사용자 닉네임 추출
                                request.setAttribute(SessionConst.LOGIN_MEMBER, new HeaderModel(nickname, gradeCode));
                            }
                        } catch (Exception e) {
                            token = null; // 토큰이 유효하지 않은 경우 null로 설정하여 refresh 토큰을 사용하도록 유도
                        }
                    }
                } else if ("refreshCookie".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                }
            }
        }

        // 유저는 존재하지만, 권한이 없을 때
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserInfoBySession user = userLoginService.loadUserByUsername(username);
            if (token != null && jwtTokenUtil.validateToken(token)&& !redisRepository.isTokenBlacklisted(token)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else if (refreshToken != null && jwtTokenUtil.validateRefreshToken(refreshToken)) {
                // refresh 토큰이 유효한 경우 새로운 access 토큰 발급
                try {
                    String refreshTokenUsername = jwtTokenUtil.getUsername(refreshToken);
                    if (refreshTokenUsername.equals(username)) {
                        UserInfoBySession refreshUser = userLoginService.loadUserByUsername(refreshTokenUsername);
                        String newAccessToken = jwtTokenUtil.generateToken(refreshUser);
                        response.addCookie(createAccessTokenCookie(newAccessToken));
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                refreshUser, null, refreshUser.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } catch (Exception e) {
                    // refresh 토큰이 유효하지 않은 경우 처리
                }
            }
        }

        chain.doFilter(request, response);
    }

    private Cookie createAccessTokenCookie(String token) {
        Cookie cookie = new Cookie("Authorization", "Bearer+ " + token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 1시간
        return cookie;
    }
}