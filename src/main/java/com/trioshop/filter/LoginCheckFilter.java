package com.trioshop.filter;

import com.trioshop.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
    private static final String[] whiteList = {"/","/login", "/css/*", "/images/*", "/itemInfo" ,"/itemList", "/SearchItems", "/cart", "/item/*" , "/join", "/findId", "/findPw", "/orders" ,"/guestLogin","/logout" };
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();

        try{

            if(isLoginCheckPath(requestURI)){
                HttpSession session = request.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER)==null){
                    response.sendRedirect("/login");
                    return;
                }
            }
            filterChain.doFilter(request, servletResponse);
        }catch (Exception e){
            throw e;
        }finally {

        }

    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}

