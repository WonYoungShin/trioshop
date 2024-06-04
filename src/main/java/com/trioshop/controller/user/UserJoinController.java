package com.trioshop.controller.user;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.GuestUserLoginInfo;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.model.dto.user.UserJoin;
import com.trioshop.service.user.UserJoinService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequiredArgsConstructor
public class UserJoinController {
    private final HttpSession session;
    private final UserJoinService userJoinService;

    @GetMapping("/join") // 회원 가입 페이지
    public String userJoinPage(@ModelAttribute UserJoin userJoin) {
        return "/user/userInfo/join";
    }
    @PostMapping("/join") // 회원가입 진행
    public String userJoinProcess(@ModelAttribute UserJoin userJoin, Model model) {
        try {
            boolean isRegistered = userJoinService.saveUserInfo(userJoin);

            // exception 처리
            if (isRegistered) {
                model.addAttribute("success", "회원가입에 성공했습니다.");
                return "redirect:/login";
            } else {
                model.addAttribute("error", "이미 사용중인 계정입니다.");
                return "redirect:/join";
            }
        } catch (Exception e) {
            model.addAttribute("exception", "회원가입 중 오류가 발생했습니다.");
            return "redirect:/join";
        }
        //여기까지전부
    }
    //@ModelAttribute 폼에서입력하면 컨트롤러로 전달~~
    @GetMapping("/guestLogin")
    public String guestLoginPage() {
        return "/user/userInfo/guestLogin";
    }

    @PostMapping("/guestLogin")
    public String guestLogin(@ModelAttribute GuestUserLoginInfo guestUserLoginInfo) {
        // 게스트 로그인 시도
        UserInfoBySession guestUser = userJoinService.guestUserLoginProcess(guestUserLoginInfo);
        session.setAttribute(SessionConst.LOGIN_MEMBER, guestUser);

        return "redirect:/";
    }
}
