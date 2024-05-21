package com.trioshop.controller.user;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public String loginPage() {
        return "/user/userInfo/login";
    }

    @PostMapping("/login")
    public ModelAndView login(String userId, String userPasswd) {
        UserInfoBySession user = userInfoService.isValidUser(userId, userPasswd);
        if (user == null || user.getGradeCode() == 0) {
            ModelAndView modelAndView = new ModelAndView("/user/userInfo/login");
            modelAndView.addObject("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return modelAndView;
        } else if (user.getGradeCode() == 4) {
            return new ModelAndView("redirect:/trioAdmin");
        } else {
            return new ModelAndView("/etc/homePage");
        }
    }

    @RequestMapping("/join")
    public String joinPage() {
        return "/user/userInfo/join";
    }

    @RequestMapping("/findId")
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

    @RequestMapping("/findPw")
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
