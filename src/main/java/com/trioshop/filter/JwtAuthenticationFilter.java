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
import java.net.URLEncoder;

import static com.trioshop.JWTConst.*;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserLoginService userLoginService;
    private final RedisRepository redisRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 요청 쿠키에서 JWT 토큰 추출

        if (REQUEST_LOGOUT.equals(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        String token = null;
        String username = null;
        String nickname = null;
        Long gradeCode = null;
        Long userCode = null;
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (ACCESS_TOKEN.equals(cookie.getName())) {
                        String header = cookie.getValue();

                        if (header != null && (header.startsWith(ACCESS_TOKEN_START1) || header.startsWith(ACCESS_TOKEN_START2))) {
                            token = header.substring(7);
                            try {
                                username = jwtTokenUtil.getUsername(token); // 토큰에서 사용자 이름 추출
                                if (jwtTokenUtil.validateToken(token) && !redisRepository.isTokenBlacklisted(token)) {
                                    userCode = jwtTokenUtil.getUserCode(token);
                                    gradeCode = jwtTokenUtil.getGradeCode(token); // 토큰에서 사용자 등급 추출
                                    nickname = jwtTokenUtil.getUserNickname(token); // 토큰에서 사용자 닉네임 추출
                                    request.setAttribute(SessionConst.LOGIN_MEMBER, new HeaderModel(userCode, nickname, gradeCode));
                                }
                            } catch (Exception e) {
                                token = null; // 토큰이 유효하지 않은 경우 null로 설정하여 refresh 토큰을 사용하도록 유도
                            }
                        }
                    } else if (REFRESH_TOKEN.equals(cookie.getName())) {
                        refreshToken = cookie.getValue();
                    }
                }
            }


            if (token == null && refreshToken != null && jwtTokenUtil.validateRefreshToken(refreshToken)) {
                // refresh 토큰이 유효한 경우 새로운 access 토큰 발급
                try {
                    String newAccessToken = jwtTokenUtil.refreshToken(refreshToken);
                    response.addCookie(createAccessTokenCookie(newAccessToken));
                    response.sendRedirect(request.getRequestURI());
                    return;
                } catch (Exception e) {
                    // refresh 토큰이 유효하지 않은 경우 처리
                }
            }

            // 유저는 존재하지만, 권한이 없을 때
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserInfoBySession user = userLoginService.loadUserByUsername(username);
                if (token != null && jwtTokenUtil.validateToken(token) && !redisRepository.isTokenBlacklisted(token)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }


        chain.doFilter(request, response);
    }

    private Cookie createAccessTokenCookie(String token) {
        Cookie cookie = new Cookie(ACCESS_TOKEN, URLEncoder.encode(ACCESS_TOKEN_START1 + token, UTF_8));
        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60*5); // 5분
        return cookie;
    }
}