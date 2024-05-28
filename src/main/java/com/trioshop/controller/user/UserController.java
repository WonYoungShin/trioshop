package com.trioshop.controller.user;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.ui.Model;
import com.trioshop.service.user.UserInfoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    HttpSession session;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/login")
    public String loginPage() {
        return "/user/userInfo/login";
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UserIdPasswd userIdPasswd) {
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
    // 첫 번째 단계:/findPw POST 요청 핸들러 메소드에서 새 비밀번호와 비밀번호 확인을 @RequestParam(required = false)로 설정하여 필수가 아님을 나타냈습니다.
    public ModelAndView findPw(@RequestParam String userName,
                               @RequestParam String userId,
                               @RequestParam(required = false) String newPassword,
                               @RequestParam(required = false) String confirmPassword) {
        ModelAndView modelAndView = new ModelAndView("/user/userInfo/findPw");

        if (newPassword == null || confirmPassword == null) {
            // 새 비밀번호와 비밀번호 확인이 입력되지 않은 경우
            modelAndView.addObject("showForm", true);
            modelAndView.addObject("userName", userName);
            modelAndView.addObject("userId", userId);
            return modelAndView;
        }

        // 새 비밀번호와 비밀번호 확인이 입력된 경우
        if (!newPassword.equals(confirmPassword)) {
            modelAndView.addObject("message", "새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return modelAndView;
        }

        UserFindPw userFindPw = new UserFindPw(userName, userId, newPassword);
        boolean isUpdated = userInfoService.findAndUpdatePw(userFindPw);
        if (isUpdated) {
            modelAndView.setViewName("redirect:/login");
            modelAndView.addObject("message", "비밀번호가 성공적으로 변경되었습니다.");
        } else {
            modelAndView.addObject("message", "비밀번호 변경 중 오류가 발생했습니다.");
        }

        return modelAndView;
    }


    @GetMapping("/myPage")
    public String myPage() {
        return "/user/userInfo/myPage";
    }

    @GetMapping("/changeInfo")
    public ModelAndView changeInfoPage(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        UserInfoBySession currentUser = (UserInfoBySession) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (currentUser == null) {
            System.out.println("확인2 = " + currentUser); //SessionAttribute 을 사용해서 loginMember에서 session을 불러옴 currentUser 로 이름정의
            mv.setViewName("redirect:/login");
            mv.addObject("error", "세션이 만료되었거나 잘못된 접근입니다.");
        } else {
            long userCode = currentUser.getUserCode();
            UserPatch userPatch = userInfoService.getUserByUserCode(String.valueOf(userCode)); // getUserByUserCode의 인자를 String으로 변환하여 전달
            if (userPatch != null) {
                mv.setViewName("/user/userInfo/changeInfo");
                mv.addObject("userPatch", userPatch);
            } else {
                mv.setViewName("redirect:/login");
                mv.addObject("error", "사용자 정보를 찾을 수 없습니다.");
            }
        }
        return mv;
    }


    @PostMapping("/changeInfo")
    public ModelAndView changeInfoPage(@ModelAttribute UserPatch userPatch, @SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession currentUser) {

        currentUser = (UserInfoBySession) session.getAttribute(SessionConst.LOGIN_MEMBER);
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
