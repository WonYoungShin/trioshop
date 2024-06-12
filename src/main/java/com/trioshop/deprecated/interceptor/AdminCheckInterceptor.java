package com.trioshop.deprecated.interceptor;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.UserInfoBySession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/");
            return false;
        }

        UserInfoBySession currentUser = (UserInfoBySession) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(currentUser == null || currentUser.getGradeCode()!=4) {
            response.sendRedirect("/");
            return false;
        }
        else{
            return true;
        }

    }
}
