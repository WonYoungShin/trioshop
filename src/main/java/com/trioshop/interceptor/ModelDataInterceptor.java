package com.trioshop.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
// 특정 상황에서 예외가 발생할 경우를 대비하여 그전 컨트롤러의 내용을 저장
public class ModelDataInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null
                && (request.getRequestURI().contains("/orders")
                || request.getRequestURI().contains("/join")
        )) { // 특정 요청 URL 에 대해서만 모델 데이터를 저장
            request.getSession().setAttribute("savedModel", modelAndView.getModelMap());
            System.out.println("Saved Model Data: " + modelAndView.getModelMap());
        }
    }
}
