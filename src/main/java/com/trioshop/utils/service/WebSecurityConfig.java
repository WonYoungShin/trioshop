package com.trioshop.utils.service;

import com.trioshop.SessionConst;
import com.trioshop.filter.JwtAuthenticationFilter;
import com.trioshop.utils.handler.LoginFailureHandler;
import com.trioshop.utils.handler.LoginSuccessHandler;
import com.trioshop.utils.handler.LogoutCustomHandler;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

/**
 * 시큐리티 설정 파일
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig{
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenUtil jwtTokenUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //Csrf 비활성화 세션+쿠키에는 필요 하지만 JWT 적용시엔 무의미 함
                .csrf(csrf -> csrf.disable())
                /**
                 * 권한별 URL 관련 설정
                 */
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers("/myPage", "/orders", "/orderList", "/addCart", "/board").hasAnyRole(Role.USER.name(),Role.ADMIN.name())
                                        //로그인(비회원, 회원, 어드민)한 사용자만 접근 가능한 URL
                        .requestMatchers("/trioAdmin/**", "/trioAdmin").hasRole(Role.ADMIN.name())
                                        //관리자만 접근 가능한 URL
                        .anyRequest().permitAll()

                        //이 외 요청 모두 허용
                )
                //Filter 추가
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
/**
 * 폼 로그인 관련 설정
 */

                .formLogin(formLogin -> formLogin
                        .loginPage("/login") //로그인 Page URL
                        .usernameParameter("userId") //ID 필드 값
                        .passwordParameter("userPasswd") //PW 필드 값
                        .loginProcessingUrl("/login") //로그인 처리 URL
                        .successHandler(new LoginSuccessHandler(jwtTokenUtil)) //성공시 로직
                        .failureHandler(new LoginFailureHandler()) //실패시 로직
                        .permitAll() //접근 권한
                )
                .logout(logout -> logout
                                .logoutUrl("/logout")
                                .addLogoutHandler(new LogoutCustomHandler(jwtTokenUtil))
//                        .logoutSuccessUrl("/") //로그아웃 성공시 URL
//                        .invalidateHttpSession(true) //로그 아웃시 세션 만료 설정 (현 시점 세션+쿠키)
                        .permitAll()  //접근 권한
                )

                /**
                 * 시큐리티 403(권한 없음)관련 예외 처리
                 */
                .exceptionHandling(exceptionConfig -> exceptionConfig
                        .authenticationEntryPoint(unauthorizedEntryPoint) //인증 안된 사용자가 접근시 호출되는 메서드
                        .accessDeniedHandler(accessDeniedHandler) //인증이 됫지만 권한이 없는 사용자가 접근시 호출되는 메서드
                )
                /**
                 * JWT 방식 시큐리티 설정
                 */
                //JWT 방식 적용시 필수!!! JWT 자체가 stateless 하게 관리하기 위함
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return http.build();
    }

    /**
     * 암호화 알고리즘 메서드 (60자)
     * 사용 방법:
     * 필요한 클래스에서 PasswordEncoder 주입 받은 후 encode 메서드 호출 (매개변수 String)
     * 예시 :
     *          - private final PasswordEncoder passwordEncoder
     *          - password = passwordEncoder.encode(password)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 시큐리티 적용 후 페이지 실행이 안됨 :
     *   로그기록 : URL에 //가 포함 돼있음  - 이를 시큐리티에서 허용하지 않음의 문제
     *   allowUrlEncodedSlashHttpFirewall 메서드를 통한 해결
     *   개발자 예측 : http:// 의 부분
     */
//    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);  // 이 설정을 추가하여 "//" 문자열을 허용합니다.
        return firewall;
    }

    /**
     * @param firewall
     */
//    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(HttpFirewall firewall) {
        return (web) -> web.httpFirewall(firewall);
    }

    /**
     * 인가 되지 않는 사이트 접속시 이동할 Page
     */

    private final AuthenticationEntryPoint unauthorizedEntryPoint =
            (request, response, authException) -> {
                HttpSession session = request.getSession();
                session.setAttribute(SessionConst.ERROR_MESSAGE,"로그인 후 접근 가능합니다.");
                response.sendRedirect("/login");
            };

    /**
     * 시큐리티 403(권한 없음)관련 예외 처리 관련 메서드와 객체
     */
    private final AccessDeniedHandler accessDeniedHandler =
            (request, response, accessDeniedException) -> {
                HttpSession session = request.getSession();
                session.setAttribute(SessionConst.ERROR_MESSAGE,"권한이 부족합니다.");
                response.sendRedirect("/");
            };


    @Getter
    @RequiredArgsConstructor
    public static class ErrorResponse {
        private final HttpStatus status;
        private final String message;
    }

}
