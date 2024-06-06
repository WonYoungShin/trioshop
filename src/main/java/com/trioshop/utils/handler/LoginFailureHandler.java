package com.trioshop.utils.handler;

import com.trioshop.SessionConst;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "아이디/비밀번호가 일치하지 않습니다.";

        // 세션에 에러 메시지를 저장합니다.
        request.getSession().setAttribute(SessionConst.ERROR_MESSAGE, errorMessage);

        // sendRedirect를 사용하여 리다이렉션합니다.
        response.sendRedirect(request.getContextPath() + "/login?error");
    }
}

