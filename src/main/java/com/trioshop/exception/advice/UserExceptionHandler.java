package com.trioshop.exception.advice;

import com.trioshop.exception.MatchingFailedPassword;
import com.trioshop.exception.SessionExpirationException;
import com.trioshop.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String UserNotFoundException(UserNotFoundException e, HttpServletRequest request, Model model){
        String requestURI = request.getRequestURI();
        String message="요청하신 정보를 찾을 수 없습니다.";
        if(requestURI.contains("login")){
            message = "아아디/패스워드가 틀렸습니다.";
        }
        model.addAttribute("message", message);
        return "/user/userInfo/"+requestURI;
    }

    @ExceptionHandler(SessionExpirationException.class)
    public String SessionExpirationException(UserNotFoundException e, HttpServletRequest request, Model model){
        model.addAttribute("message",e.getMessage());
        return "/user/userInfo/"+request.getRequestURI();
    }

    @ExceptionHandler(MatchingFailedPassword.class)
    public String MatchingFailedPassword(MatchingFailedPassword e, Model model) {
        model.addAttribute("message", "패스워드가 일치하지 않습니다.");
        return "/user/userInfo/passwordCheckForm";
    }
}
