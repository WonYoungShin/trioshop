package com.trioshop.controller.exception.advice;

import com.trioshop.controller.exception.SessionExpirationException;
import com.trioshop.controller.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
    static final String errorMessage = "?message=NotFound";
    @ExceptionHandler(UserNotFoundException.class)
    public String UserNotFoundException(UserNotFoundException e, HttpServletRequest request){
        return "redirect:"+request.getRequestURI()+errorMessage;
    }

    @ExceptionHandler(SessionExpirationException.class)
    public String SessionExpirationException(UserNotFoundException e, HttpServletRequest request){
        return request.getRequestURI()+errorMessage;
    }
}
