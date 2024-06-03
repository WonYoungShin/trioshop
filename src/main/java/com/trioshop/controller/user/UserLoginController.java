package com.trioshop.controller.user;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.UserIdPasswd;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.service.user.UserInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@RequiredArgsConstructor
public class UserLoginController {

    private final HttpSession session;
    private final UserInfoService userInfoService;

    @GetMapping("/login")
    public String loginPage_G(@RequestParam(value = "error", required = false)String error, Model model) {
        if(Objects.nonNull(error)){
            model.addAttribute("error","정보를 찾을 수 없습니다.");
        }
        return "/user/userInfo/login";
    }

    //@ModelAttribute 객체를 받아오는거 @RequestParam 변수명을 가져오는거
    @PostMapping("/login")
    public String loginPage(@ModelAttribute UserIdPasswd userIdPasswd) {
        UserInfoBySession user = userInfoService.isValidUser(userIdPasswd);
        String redirectURI="/";
        session.setAttribute(SessionConst.LOGIN_MEMBER, user);
        //스프링이 자동으로 관리하는 세션 객체에 속성이 설정됩니다. 이렇게 하면 사용자가 로그인한 정보를 세션에 저장할 수 있습니다.
        if (user.getGradeCode() == 4) {
            redirectURI = "/trioAdmin";
        }
        return "redirect:"+redirectURI;
    }


    @GetMapping("/logout")
    public String logoutPage(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
