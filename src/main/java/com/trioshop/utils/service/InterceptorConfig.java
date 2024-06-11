package com.trioshop.utils.service;

import com.trioshop.interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

/**
 * 로그 인터셉터
 */
        registry.addInterceptor(new LogInterceptor())
                        .order(1)
                        .addPathPatterns("/**")
                         .excludePathPatterns("/css/**", "/*.ico", "/error","/images/**");
/**
 * 비밀번호 변경시에 2개 비밀번호가 확인하는 인터셉터
 */
        registry.addInterceptor(new PasswordCheckInterceptor())
                .order(2)
                .addPathPatterns("/changeInfo","/changeInfo/**","/changePassword","/changePassword/**");
        /**
         * 주문 요청에 대한 모델 데이터 저장 인터셉터
         */
        registry.addInterceptor(new ModelDataInterceptor())
                .order(3)
                .addPathPatterns("/orders", "/join");

/**
 * 시큐리티 적용으로 인한 권한 체크 인터셉터 주석 처리
 */
//        registry.addInterceptor(new LoginCheckInterceptor())
//                .order(2)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/","/login", "/css/**", "/images/*", "/itemInfo" ,"/itemList",
//                        "/SearchItems", "/cart", "/item/**" , "/join", "/findId",
//                        "/findPw", "/guestLogin","/logout", "/*.ico","/error","/updatePw");
//
//        registry.addInterceptor(new AdminCheckInterceptor())
//                .order(2)
//                .addPathPatterns("/trioAdmin/**")
//                .excludePathPatterns("/","/login", "/css/**", "/images/*","/*.ico");
    }
}
