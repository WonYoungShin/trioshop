package com.trioshop.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @RequestMapping("/login")
    public String itemDetailPage () {
        return "/user/itemInfo/itemList";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String password) {
        // 로그인 처리 로직
        if (isValidUser(userId, password)) {
            return "redirect:/dashboard"; // 로그인 성공 시 대시보드로 리다이렉트
        } else {
            return "redirect:/login?error"; // 로그인 실패 시 로그인 페이지로 리다이렉트
        }
    }

    private boolean isValidUser(String userId, String password) {
        // 사용자 인증 로직 구현
        return "admin".equals(userId) && "password".equals(password); // 예시: admin 계정
    }
}
