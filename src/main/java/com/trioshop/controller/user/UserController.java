package com.trioshop.controller.user;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import com.trioshop.service.user.UserInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
//
    @Autowired
    HttpSession session;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/login")
    public String loginPage() {
        return "/user/userInfo/login";
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UserIdPasswd userIdPasswd, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        UserInfoBySession user = userInfoService.isValidUser(userIdPasswd.getUserId(), userIdPasswd.getUserPasswd());

        if (user == null || user.getGradeCode() == 0) {
            mv.setViewName("/user/userInfo/login");
            mv.addObject("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return mv;
        }

        session.setAttribute(SessionConst.LOGIN_MEMBER, user);
        System.out.println("user = " + user);
        if (user.getGradeCode() == 4) {
            mv.setViewName("redirect:/trioAdmin");
            return mv;
        } else {
            mv.setViewName("redirect:/");
            return mv;
        }
    }

    @GetMapping("/logout")
    public String logoutPage() {
        if (session != null)
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
        UserFindId userId = userInfoService.isfindId(userName, userTel);
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
        UserFindPw userPw = userInfoService.isfindPw(userName, userId);
        ModelAndView modelAndView = new ModelAndView("/user/userInfo/findPw");
        if (userPw != null) {
            modelAndView.addObject("userInfo", userPw);
        } else {
            modelAndView.addObject("message", "일치하는 정보를 찾을 수 없습니다.");
        }
        return modelAndView;
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "/user/userInfo/myPage";
    }

    @GetMapping("/changeInfo")
    public ModelAndView changeInfoPage() {
        ModelAndView mv = new ModelAndView();
        UserInfoBySession currentUser = (UserInfoBySession) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (currentUser == null) {
            mv.setViewName("redirect:/login");
            mv.addObject("error", "세션이 만료되었거나 잘못된 접근입니다.");
        } else {
            UserPatch userPatch = new UserPatch();
            userPatch.setUserCode(currentUser.getUserCode());
            userPatch.setUserNickname(currentUser.getUserNickname());
            // userPatch에 필요한 다른 정보를 설정
            mv.setViewName("/user/userInfo/changeInfo");
            mv.addObject("userPatch", userPatch);
        }
        return mv;
    }

    @PostMapping("/changeInfo")
    public ModelAndView changeInfoPage(@ModelAttribute UserPatch userPatch) {
        System.out.println("userPatch = " + userPatch);
        UserInfoBySession currentUser = (UserInfoBySession) session.getAttribute(SessionConst.LOGIN_MEMBER);
        userPatch.setUserCode(currentUser.getUserCode());

        ModelAndView mv = new ModelAndView();
        try {

            boolean isUpdated = userInfoService.patchUser(userPatch);

            if (isUpdated) {
                mv.setViewName("redirect:/myPage");
            } else {
                mv.setViewName("redirect:/changeInfo");
                mv.addObject("error", "정보 수정 중 오류가 발생했습니다.");
            }
            return mv;
        } catch (Exception e) {
            mv.setViewName("redirect:/changeInfo");
            mv.addObject("error", "예외발생");
            return mv;
        }
    }

}
