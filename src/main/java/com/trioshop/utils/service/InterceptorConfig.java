package com.trioshop.utils.service;

import com.trioshop.interceptor.AdminCheckInterceptor;
import com.trioshop.interceptor.LogInterceptor;
import com.trioshop.interceptor.LoginCheckInterceptor;
import com.trioshop.interceptor.PasswordCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                        .order(1)
                        .addPathPatterns("/**")
                         .excludePathPatterns("/css/**", "/*.ico", "/error","/images/**");

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

        registry.addInterceptor(new PasswordCheckInterceptor())
                .order(2)
                .addPathPatterns("/changeInfo","/changeInfo/**","/changePassword","/changePassword/**");
    }
}
