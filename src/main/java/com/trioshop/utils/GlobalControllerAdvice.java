package com.trioshop.utils;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.UserInfoBySession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
//전역적으로 컨트롤러에 대한 공통 작업을 수행할 수 있게 해주는 어노테이션
public class GlobalControllerAdvice {
    @Autowired
    private HttpSession session;

    // 코드 간결화를 위하여 @ControllerAdvice를 사용하여
    // 유저 세션정보를 파라미터로 불러옴
    @ModelAttribute("userInfoBySession")
    public UserInfoBySession getUserInfoBySession() {
        return (UserInfoBySession) session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
