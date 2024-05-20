package com.trioshop.controller.user;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView login(@RequestParam String userId, @RequestParam String userPasswd) {
        UserInfoBySession user = userInfoService.isValidUser(userId, userPasswd);
        if (user.getGradeCode() != 0) {
//            System.out.println("User information: " + user);
            return new ModelAndView("redirect:/join");  // redirect to a home page or dashboard
        } else {
            ModelAndView modelAndView = new ModelAndView("/user/userInfo/login");
            modelAndView.addObject("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return modelAndView;
        }
    }

    @RequestMapping("/join")
    public String joinPage() {
        return "/user/userInfo/join";
    }

//    @RequestMapping("/orderlist")
//    public String orderlistPage() {
//        return "/user/userInfo/orderlist";
//    }
//
//    @RequestMapping("/guestLogin")
//    public String guestLoginage() {
//        return "/user/userInfo/guestLogin";
//    }
//
//    @RequestMapping("/findId")
//    public String findIdage() {
//        return "/user/userInfo/findId";
//    }
//
//    @RequestMapping("/findPw")
//    public String findPwage() {
//        return "/user/userInfo/findPw";
//    }
//
//    @RequestMapping("/myPage")
//    public String myPage() {
//        return "/user/userInfo/myPage";
//    }
//
//    @RequestMapping("/changeInfo")
//    public String myPage() {
//        return "/user/userInfo/changeInfo";
//    }

}
