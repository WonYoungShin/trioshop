package com.trioshop.controller.user;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.UserIdPasswd;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.model.dto.user.UserJoin;
import com.trioshop.model.dto.user.UserPatch;
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
        }
        else {

            mv.setViewName("redirect:/");
            return mv;
        }
    }

    @GetMapping("/logout")
    public String logoutPage() {
        if(session!=null)
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
                // 회원가입에 실패한 경우
                mv.setViewName("redirect:/join");
                // 오류 메시지를 alert 창으로 표시
                mv.addObject("error", "이미 사용중인 계정입니다.");
            }
        } catch (Exception e) {
            // 회원가입 중 오류가 발생한 경우
            mv.setViewName("redirect:/join");
            // 오류 메시지를 alert 창으로 표시
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


    @GetMapping("/myPage")
    public String myPage() {
        return "/user/userInfo/myPage";
    }


    @GetMapping("/changeInfo")
    public String changeInfoPage(HttpSession session, Model model) {
        // 세션에 저장된 객체 가져오기
        Object sessionAttribute = session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 저장된 객체가 UserPatch 타입인지 확인
        if (sessionAttribute instanceof UserPatch) {
            UserPatch currentUser = (UserPatch) sessionAttribute;
            model.addAttribute("currentUser", currentUser);
            return "/user/userInfo/changeInfo";
        } else {
            // UserPatch 타입이 아닌 경우 로그인 페이지로 리디렉션
            return "redirect:/login";
        }
    }



    @PostMapping("/changeInfo")
    public ModelAndView changeInfoPage(@RequestParam String newUserPasswd, @ModelAttribute UserPatch userPatch) {
        ModelAndView mv = new ModelAndView();
        try {
            UserPatch currentUser = (UserPatch) session.getAttribute(SessionConst.LOGIN_MEMBER);

            // 사용자가 변경한 정보가 있는지 확인
            if (!userInfoService.changedInfo(userPatch)) {
                // 변경할 정보가 없으면 에러 메시지 표시
                mv.setViewName("redirect:/changeInfo");
                mv.addObject("error", "변경할 정보를 입력하세요.");
                return mv;
            }

            // 사용자의 이름은 변경할 수 없는 값으로 설정
            userPatch.setUserName(currentUser.getUserName());
            userPatch.setUserCode(currentUser.getUserCode()); // 현재 사용자의 코드 설정
            userPatch.setUserPasswd(newUserPasswd); // 새 비밀번호 설정

            // 사용자가 변경한 정보를 데이터베이스에 업데이트
            boolean isUpdated = userInfoService.patchUser(userPatch);
            if (isUpdated) {
                // 업데이트 성공 시
                mv.setViewName("redirect:/myPage");
            } else {
                // 업데이트 실패 시
                mv.setViewName("redirect:/changeInfo");
                mv.addObject("error", "정보 수정 중 오류가 발생했습니다.");
            }
            return mv;
        } catch (Exception e) {
            // 예외 발생 시
            mv.setViewName("redirect:/changeInfo");
            mv.addObject("error", "정보 수정 중 오류가 발생했습니다.");
            return mv;
        }
    }


}
