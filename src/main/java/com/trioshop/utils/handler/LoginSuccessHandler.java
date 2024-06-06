package com.trioshop.utils.handler;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.utils.service.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * 로그인 성공시 호출 되는 클래스(핸들러)
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserInfoBySession user =(UserInfoBySession) authentication.getPrincipal();
        user = UserInfoBySession.builder()
                .userCode(user.getUserCode())
                .gradeCode(user.getGradeCode())
                .userId(user.getUserId())
                .userNickname(user.getUserNickname())
                .userPasswd("")
                .role(user.getRole())
                .build();

        session.setAttribute(SessionConst.LOGIN_MEMBER,user);
        if(user.getRole().equals(Role.ADMIN)){
            response.sendRedirect("/trioAdmin");
        }else{
            response.sendRedirect("/");
        }
    }
}
