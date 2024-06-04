package com.trioshop.interceptor;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.UserInfoBySession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class PasswordCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null) {
            boolean checked = (boolean) session.getAttribute("passwordChecked");
            UserInfoBySession userInfoBySession = (UserInfoBySession) session.getAttribute(SessionConst.LOGIN_MEMBER);
            if (checked) {
                return true;
            }else{
                response.sendRedirect("/passwordCheck/" + userInfoBySession.getUserCode());
                return false;
            }
        }
        response.sendRedirect("/login");
        return false;
    }
}
