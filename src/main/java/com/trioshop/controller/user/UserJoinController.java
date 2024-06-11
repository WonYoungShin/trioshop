package com.trioshop.controller.user;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.GuestUserLoginInfo;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.model.dto.user.UserJoin;
import com.trioshop.service.user.UserJoinService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class UserJoinController {
    private final HttpSession session;
    private final UserJoinService userJoinService;

    @GetMapping("/join") // 회원 가입 페이지
    public String userJoinPage(@ModelAttribute UserJoin userJoin) {
        return "/user/userInfo/join";
    }
    @PostMapping("/join") // 회원가입 진행
    public String userJoinProcess(@ModelAttribute UserJoin userJoin, Model model) {
        boolean userIdExists = userJoinService.userJoinProcess(userJoin);
        if(userIdExists) { // 유저가 존재한다면 기족 작성기록을 가지고 다시 회원가입페이지로
            return "/user/userInfo/join";
        } else { // 로그인 페이지로
            return "redirect:/login";
        }
    }
    @GetMapping("/join/terms")
    public String userJoinTerms(){
        return "/user/userInfo/joinOfTerms";
    }


    @GetMapping("/guestLogin")
    public String guestLoginPage() {
        return "/user/userInfo/guestLogin";
    }

    @PostMapping("/guestLogin")
    public String guestLogin(@ModelAttribute GuestUserLoginInfo guestUserLoginInfo, HttpServletResponse response) {
        // 게스트 로그인 시도
        UserInfoBySession guestUser = userJoinService.guestUserLoginProcess(guestUserLoginInfo);
        userJoinService.guestUserJwtToken(response,guestUser);
        return "redirect:/";
    }
}
