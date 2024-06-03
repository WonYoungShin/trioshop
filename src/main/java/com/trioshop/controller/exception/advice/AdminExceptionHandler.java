package com.trioshop.controller.exception.advice;

import com.trioshop.controller.exception.DontSaveException;
import com.trioshop.controller.exception.QuantityAdjustFailed;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AdminExceptionHandler {

    @ExceptionHandler(DontSaveException.class)
    public ModelAndView DontSaveException(DontSaveException e) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("error", e.getMessage());
        return mav;
    }
    @ExceptionHandler(QuantityAdjustFailed.class)
    public ModelAndView QuantityAdjustFailed(QuantityAdjustFailed e) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("error", e.getMessage());
        return mav;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView NoSuchElementException(NoSuchElementException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", e.getMessage());
        return mav;
    }

}
