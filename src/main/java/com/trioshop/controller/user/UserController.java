package com.trioshop.controller.user;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.*;
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
    public ModelAndView findId(@RequestParam String userName, @RequestParam String userTel) {
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
    public ModelAndView findPw(@RequestParam String userName, @RequestParam String userId, @RequestParam(required = false) String newPassword, @RequestParam(required = false) String confirmPassword) {
        ModelAndView modelAndView = new ModelAndView("/user/userInfo/findPw");
        try {
            // 이름과 아이디로 비밀번호 찾기 시도
            UserFindPw userFindPw = new UserFindPw(userName, userId, null); // 비밀번호는 필요없음
            boolean result = userInfoService.findAndUpdatePw(userFindPw);

            // 사용자 정보가 없을 경우
            if (!result) {
                modelAndView.addObject("message", "일치하는 정보를 찾을 수 없습니다.");
            } else {
                // 새 비밀번호와 비밀번호 확인이 입력된 경우
                if (newPassword != null && confirmPassword != null) {
                    if (!newPassword.equals(confirmPassword)) {
                        modelAndView.addObject("message", "새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                    } else {
                        // 새 비밀번호를 업데이트
                        userFindPw.setUserPasswd(newPassword);
                        boolean isUpdated = userInfoService.findAndUpdatePw(userFindPw);
                        if (isUpdated) {
                            modelAndView.setViewName("redirect:/login");
                            modelAndView.addObject("message", "비밀번호가 성공적으로 변경되었습니다.");
                        } else {
                            modelAndView.addObject("message", "비밀번호 변경 중 오류가 발생했습니다.");
                        }
                    }
                } else {
                    // 새 비밀번호와 비밀번호 확인이 입력되지 않은 경우
                    modelAndView.addObject("showForm", true);
                    modelAndView.addObject("userName", userName);
                    modelAndView.addObject("userId", userId);
                }
            }
        } catch (Exception e) {
            modelAndView.addObject("message", "예외 발생");
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
    public ModelAndView changeInfo(@ModelAttribute UserPatch userPatch) {
        ModelAndView mv = new ModelAndView();
        try {
            UserInfoBySession currentUser = (UserInfoBySession) session.getAttribute(SessionConst.LOGIN_MEMBER);

            if (currentUser == null) {
                mv.setViewName("redirect:/login");
                mv.addObject("error", "세션이 만료되었거나 잘못된 접근입니다.");
                return mv;
            }

            // 입력값이 모두 비어있는지 확인
            if (!userInfoService.changedInfo(userPatch)) {
                mv.setViewName("redirect:/changeInfo");
                return mv;
            } // Validation 적용 시 삭제

            // 세션에서 현재 사용자 정보 가져오기
            userPatch.setUserCode(currentUser.getUserCode());

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

    //@ModelAttribute 폼에서입력하면 컨트롤러로 전달~~
    @GetMapping("/guestLogin")
    public String guestLoginPage() {
        return "/user/userInfo/guestLogin";
    }

    @PostMapping("/guestLogin")
    public ModelAndView guestLogin(@ModelAttribute GuestUserJoin guestUserJoin, @ModelAttribute GuestUserJoin2 guestUserJoin2) {
        ModelAndView mv = new ModelAndView();

        // 첫 번째 로그인 시도
        GuestUserJoin existingUser = userInfoService.LoginGuestUser(guestUserJoin);

        // 기존 사용자가 있고 grade_code가 0인 경우에만 로그인 성공
        if (existingUser != null && existingUser.getGradeCode() == 0) {
            mv.setViewName("redirect:/");
        } else {
            // guestUserJoin 객체에 필요한 값 설정
            guestUserJoin.setGradeCode(0); // 예시로 gradeCode를 설정하고, 필요한 다른 값들도 설정해야 함

            // 중복된 DB가 없으면 회원가입을 시도
            boolean isSuccess = userInfoService.saveGuestUser(guestUserJoin, guestUserJoin2);
            if (isSuccess) {
                // 회원가입이 완료되면 자동으로 로그인
                mv.addObject("message", "회원가입이 완료되었습니다. 로그인되었습니다.");
            } else {
                mv.addObject("message", "로그인에 실패했습니다. 다시 시도해주세요.");
            }
            mv.setViewName("redirect:/");
        }
        // 나중에 수정해야할 부분
        UserInfoBySession sessionUser = new UserInfoBySession();
        sessionUser.setUserNickname("게스트유저");
        sessionUser.setUserCode(guestUserJoin.getUserCode());
        sessionUser.setGradeCode(1);
        session.setAttribute(SessionConst.LOGIN_MEMBER, sessionUser);
        ////수정
        return mv;
    }

}
