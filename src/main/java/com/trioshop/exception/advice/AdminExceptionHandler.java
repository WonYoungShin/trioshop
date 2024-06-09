package com.trioshop.exception.advice;

import com.trioshop.exception.DontSaveException;
import com.trioshop.exception.QuantityAdjustFailed;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AdminExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ModelAndView DontSaveException(ResponseStatusException e) {
        ModelAndView mav = new ModelAndView();
        String message = e.getMessage();
        mav.setViewName("/admin/addItem");
        mav.addObject("errorMessage", message);
        return mav;
    }

//    @ExceptionHandler(DontSaveException.class)
//    public ModelAndView DontSaveException(DontSaveException e) {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("/admin/addItem");
//        mav.addObject("error", "저장 실패");
//        return mav;
//    }


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
