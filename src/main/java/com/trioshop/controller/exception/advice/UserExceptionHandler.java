package com.trioshop.controller.exception.advice;

import com.trioshop.controller.exception.MatchingFailedPassword;
import com.trioshop.controller.exception.SessionExpirationException;
import com.trioshop.controller.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String UserNotFoundException(@RequestParam(defaultValue = "", required = false) String status, UserNotFoundException e, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String requestURI = request.getRequestURI();
        String message="요청하신 정보를 찾을 수 없습니다.";
        if(requestURI.contains("login")){
            message = "아아디/패스워드가 틀렸습니다.";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:"+requestURI+status;
    }

    @ExceptionHandler(SessionExpirationException.class)
    public String SessionExpirationException(UserNotFoundException e, HttpServletRequest request, Model model){
        model.addAttribute("message",e.getMessage());
        return request.getRequestURI();
    }

    @ExceptionHandler(MatchingFailedPassword.class)
    public String MatchingFailedPassword(@RequestParam(defaultValue = "", required = false) String status, MatchingFailedPassword e, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String requestURI = request.getRequestURI();

        redirectAttributes.addFlashAttribute("message", e.getMessage());
        return "redirect:"+requestURI+status;
    }
}
