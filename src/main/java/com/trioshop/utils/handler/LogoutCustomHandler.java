package com.trioshop.utils.handler;

import com.trioshop.utils.service.JwtTokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class LogoutCustomHandler implements LogoutHandler {
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = null;
        String username = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    String header = cookie.getValue();

                    if (header != null && (header.startsWith("Bearer+") || header.startsWith("Bearer "))) {
                        token = header.substring(7);
                        username = jwtTokenUtil.getUsername(token); // 토큰에서 사용자 이름 추출
                    }
                    break;
                }
            }
        }


       jwtTokenUtil.logout(username, token); //RefreshToken 제거 및 AccessToken 블랙리스트 추가


        // 쿠키 삭제
//        Cookie authCookie = new Cookie("Authorization", null);
//        authCookie.setPath("/");
//        authCookie.setMaxAge(0);
//        response.addCookie(authCookie);
//
//        Cookie refreshCookie = new Cookie("refreshCookie", null);
//        refreshCookie.setPath("/");
//        refreshCookie.setMaxAge(0);
//        response.addCookie(refreshCookie);

        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
