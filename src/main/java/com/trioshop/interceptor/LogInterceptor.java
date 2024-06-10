package com.trioshop.interceptor;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.UserInfoBySession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";
    public static final String SESSION_UUID = "sessionUuid";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession();
        String uuid = (String) session.getAttribute(SESSION_UUID);
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            session.setAttribute(SESSION_UUID, uuid);
        }

        // Session 정보 가져오기
        UserInfoBySession userInfo = (UserInfoBySession) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler; //호출할 컨트롤러 메서드의 모든 정보
        }

        request.setAttribute(LOG_ID, uuid);

        if (userInfo != null) {
            log.info("REQUEST [{}][{}][{}][User: {}]", uuid, requestURI, handler, userInfo.getUsername());
        } else {
            log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("postHandle [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = (String) request.getAttribute(LOG_ID);
        log.info("RESPONSE [{}][{}][{}]", uuid, requestURI, handler);

        if (ex != null) {
            log.error("afterCompletion error!!", ex);
        }

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
