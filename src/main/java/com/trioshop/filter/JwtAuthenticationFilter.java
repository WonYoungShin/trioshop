package com.trioshop.filter;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.HeaderModel;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.service.user.UserLoginService;
import com.trioshop.utils.service.JwtTokenUtil;
import com.trioshop.utils.service.Role;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 요청 쿠키에서 JWT 토큰 추출
        String token = null;
        String username = null;
        String nickname;
        Long gradeCode;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    String header = cookie.getValue();
                    if (header != null && (header.startsWith("Bearer ") || header.startsWith("Bearer+"))) {
                        token = header.substring(7);
                        username = jwtTokenUtil.getUsername(token); // 토큰에서 사용자 이름 추출
                        gradeCode = jwtTokenUtil.getGradeCode(token); // 토큰에서 사용자 등급 추출
                        nickname = jwtTokenUtil.getUserNickname(token); // 토큰에서 사용자 닉네임 추출

                        request.setAttribute(SessionConst.LOGIN_MEMBER,new HeaderModel(nickname,gradeCode));
                    }
                    break;
                }
            }
        }
        //유저는 존재하지만, 권한이 없을때
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserInfoBySession user = userLoginService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(token)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
