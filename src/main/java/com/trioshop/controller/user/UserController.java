// UserController.java
package com.trioshop.controller.user;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.model.dto.user.UserJoin;
import com.trioshop.service.user.UserInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/login")
    public String loginPage() {
        return "/user/userInfo/login";
    }

    @PostMapping("/login")
    public ModelAndView login(String userId, String userPasswd, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        UserInfoBySession user = userInfoService.isValidUser(userId, userPasswd);
        session.setAttribute("UserInfoBySession", user);

        if (user == null || user.getGradeCode() == 0) {
            mv.setViewName("/user/userInfo/login");
            mv.addObject("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return mv;
        } else if (user.getGradeCode() == 4) {
            mv.setViewName("redirect:/trioAdmin");
            return mv;
        } else {
            mv.setViewName("redirect:/");
            return mv;
        }
    }

    @GetMapping("/logout")
    public String logoutPage(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("/join")
    public String joinPage(@ModelAttribute("userJoin") UserJoin userJoin) {
        return "/user/userInfo/join";
    }

    @PostMapping("/join")
    public ModelAndView registerUserPage(@ModelAttribute("userJoin") UserJoin userJoin) {
        ModelAndView mv = new ModelAndView();
        try {
            // TRIO_USERS 테이블에 사용자 정보 저장
            boolean isRegistered = userInfoService.registerUser(userJoin);
            if (isRegistered) {
                mv.setViewName("redirect:/login");
                mv.addObject("success", "회원가입에 성공했습니다.");
            } else {
                mv.setViewName("redirect:/join");
                mv.addObject("error", "이미 사용중인 계정입니다.");
            }
        } catch (Exception e) {
            mv.setViewName("redirect:/join");
            mv.addObject("error", "회원가입 중 오류가 발생했습니다.");
        }
        return mv;
    }

    @GetMapping("/findId")
    public String findIdPage() {
        return "/user/userInfo/findId";
    }

    @PostMapping("/findId")
    public ModelAndView findId(String userName, String userTel) {
        UserInfoBySession userId = userInfoService.isfindId(userName, userTel);
        ModelAndView modelAndView = new ModelAndView("/user/userInfo/findId");
        if (userId != null) {
            modelAndView.addObject("userInfo", userId);
        } else {
            modelAndView.addObject("message", "일치하는 정보를 찾을 수 없습니다.");
        }
        return modelAndView;
    }

    @GetMapping("/findPw")
    public String findPwPage() {
        return "/user/userInfo/findPw";
    }

    @PostMapping("/findPw")
    public ModelAndView findPw(String userName, String userId) {
        UserInfoBySession userPw = userInfoService.isfindPw(userName, userId);
        ModelAndView modelAndView = new ModelAndView("/user/userInfo/findPw");
        System.out.println("userPw");
        if (userPw != null) {
            modelAndView.addObject("userInfo", userPw);
        } else {
            modelAndView.addObject("message", "일치하는 정보를 찾을 수 없습니다.");
        }
        return modelAndView;
    }
}
