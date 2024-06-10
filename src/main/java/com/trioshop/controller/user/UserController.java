package com.trioshop.controller.user;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.*;
import com.trioshop.service.user.UserInfoService;
import com.trioshop.utils.service.SecurityUtils;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final HttpSession session;

    private final UserInfoService userInfoService;
    private final SecurityUtils securityUtils;

    @GetMapping("/findId")
    public String findIdPage() {
        return "/user/userInfo/findId";
    }

    @PostMapping("/findId")
    public String findId(@ModelAttribute UserFindId userFindId, Model model) {
        String id = userInfoService.isFindId(userFindId);

        model.addAttribute("id", id);

        return "/user/userInfo/findId";
    }

    @GetMapping("/findPw")
    public String findPwPage() {
        return "/user/userInfo/findPw";
    }

    @PostMapping("/findPw")
    public String findCode(@ModelAttribute PasswordChangeCodeSelectModel psModel, Model model) {
        PasswordChangeCodeAndStatus passwordChangeCodeAndStatus = userInfoService.findUserCodeByNameAndId(psModel);

        session.setAttribute("userCode", passwordChangeCodeAndStatus.getUserCode());
        model.addAttribute("findPwSuccess", passwordChangeCodeAndStatus.getStatus());
        return "/user/userInfo/findPw";
    }

    @PostMapping("/updatePw")
    public String updatePw(@ModelAttribute PasswordCheckedModel password, @SessionAttribute Long userCode) {
        if (password.checkingPassword()) {
            userInfoService.updatePw(userCode, password.getNewPassword());
            session.invalidate();
            return "redirect:/login";
        }
        return "forward:/findPw";
    }

    @GetMapping("/myPage")
    public String myPage(Model model) {
        model.addAttribute("userCode", securityUtils.getCurrentUserCode());
        return "/user/userInfo/myPage";
    }

    @GetMapping("/passwordCheck/{userCode}")
    public String passwordCheckForm() {
        return "/user/userInfo/passwordCheckForm";
    }

    @PostMapping("/passwordCheck/{userCode}")
    public String passwordCheck(@PathVariable("userCode") Long userCode,
                                @RequestParam("status")String status,
                                @RequestParam("currentPassword") String password) {
        userInfoService.passwordCheck(userCode,password);

        session.setAttribute("passwordChecked", true);
        if(status.equals("info")){
            return "redirect:/changeInfo/" + userCode;
        }

        return "redirect:/changePassword/" + userCode;

    }

    @GetMapping("/changeInfo/{userCode}")
    public String changeInfoPage(@PathVariable("userCode") Long userCode, Model model) {
        UserPatchModel userPatchModel = userInfoService.findByUserCode(userCode);
        model.addAttribute("userPatchModel", userPatchModel);
        return "/user/userInfo/changeInfo";
    }

    @PostMapping("/changeInfo/{userCode}")
    public String changeInfo(@PathVariable("userCode") Long userCode, @ModelAttribute UserPatchModel userPatchModel) {
        userInfoService.patchUserInfo(userCode, userPatchModel);
        return "redirect:/myPage";
    }

    @GetMapping("/changePassword/{userCode}")
    public String changePasswordPage(@PathVariable("userCode") Long userCode, @ModelAttribute PasswordCheckedModel password) {
        return "/user/userInfo/changePasswordForm";
    }
    @PostMapping("/changePassword/{userCode}")
    public String changePassword(@PathVariable("userCode") Long userCode, @ModelAttribute PasswordCheckedModel password, Model model) {
        if (password.checkingPassword()) {
            userInfoService.updatePw(userCode, password.getNewPassword());
            return "redirect:/myPage";
        }
        return "/user/userInfo/changePasswordForm";
    }
}
