package com.trioshop.exception.advice;

import com.trioshop.exception.ApplicationException;
import com.trioshop.exception.ExceptionType;
import com.trioshop.exception.QuantityAdjustFailed;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final HttpSession session;

    //전역으로 예외를 관리 (Enum 활용 = ExceptionType)
    @ExceptionHandler(ApplicationException.class)
    public ModelAndView handleApplicationException(ApplicationException e) {
        ModelAndView mv = new ModelAndView();
        ExceptionType exceptionType = e.getExceptionType();
        mv.addObject("errorMessage", exceptionType.getMessage());

        // 이전 화면의 데이터저장을 필요로 하는경우
        // ModelDataInterceptor 에 URL을 저장 하여 사용 (config에도 등록해야함)
        ModelMap savedModel = (ModelMap) session.getAttribute("savedModel");
        if (savedModel != null) {
            mv.addAllObjects(savedModel);
            session.removeAttribute("savedModel"); // 세션에서 savedModel 제거
        }
        // 예외후 이동할 뷰를 지정
        mv.setViewName(exceptionType.getViewName());
        return mv;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView NoSuchElementException(NoSuchElementException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage","항목을 찾을수 없습니다.");
        return mav;
    }
    @ExceptionHandler(QuantityAdjustFailed.class)
    public ModelAndView QuantityAdjustFailed(QuantityAdjustFailed e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", e.getMessage());
        return mav;
    }
}

