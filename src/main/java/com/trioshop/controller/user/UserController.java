package com.trioshop.controller.user;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.user.*;
import com.trioshop.service.user.UserInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final HttpSession session;

    private final UserInfoService userInfoService;

    @GetMapping("/findId")
    public String findIdPage(@RequestParam(value = "message", required = false) String message, Model model) {
        if(Objects.nonNull(message)){
            model.addAttribute("message","정보를 찾을 수 없습니다.");
        }
        return "/user/userInfo/findId";
    }

    @PostMapping("/findId")
    public String findId(@ModelAttribute UserFindId userFindId, Model model) {
        String id = userInfoService.isFindId(userFindId);

        model.addAttribute("id", id);

        return "/user/userInfo/findId";
    }

    @GetMapping("/findPw")
    public String findPwPage(@RequestParam(value = "message", required = false) String message, Model model) {
        if(Objects.nonNull(message)){
            model.addAttribute("message","정보를 찾을 수 없습니다.");
        }
        return "/user/userInfo/findPw";
    }

    @PostMapping("/findPw")
    public String findCode(@ModelAttribute PasswordChangeCodeSelectModel psModel, Model model) {
        PasswordChangeCodeAndStatus passwordChangeCodeAndStatus = userInfoService.findUserCodeByNameAndId(psModel);

        session.setAttribute("userCode", passwordChangeCodeAndStatus.getUserCode());
        model.addAttribute("showForm", passwordChangeCodeAndStatus.getStatus());
        return "/user/userInfo/findPw";
    }

    @PostMapping("/updatePw")
    public String updatePw(@ModelAttribute PasswordCheckedModel password, @SessionAttribute Long userCode) {
        if(password.checkingPassword()){
            userInfoService.updatePw(userCode, password.getNewPassword());
            session.invalidate();
            return "redirect:/login";
        }
        return "forward:/user/userInfo/findPw";
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


}
