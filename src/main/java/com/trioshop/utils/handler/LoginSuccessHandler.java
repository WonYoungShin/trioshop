package com.trioshop.utils.service;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.UserInfoBySession;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class loginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserInfoBySession userInfoBySession =(UserInfoBySession) authentication.getPrincipal();
        System.out.println(userInfoBySession);
        session.setAttribute(SessionConst.LOGIN_MEMBER,userInfoBySession);
        if(userInfoBySession.getRole().equals(Role.ADMIN)){
            response.sendRedirect("/trioAdmin");
        }else{
            response.sendRedirect("/");
        }
    }
}
