package com.trioshop.utils.service;

import com.trioshop.model.dto.user.UserInfoBySession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    // 현재 인증된 사용자 정보를 가져오는 메서드
    public UserInfoBySession getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          if(authentication != null && authentication.getPrincipal() instanceof UserInfoBySession) {
              return (UserInfoBySession) authentication.getPrincipal();

           }
            return null; //throw 처리 가능
    }

    //userCode만을 가져오기 위한 메서드 필요한 메서드로 확장해서 사용하게끔 SampleCode
    //사용 예제는 UserController에 작성
    public Long getCurrentUserCode() {
        UserInfoBySession user = getCurrentUser();
        if(user != null) {
            System.out.println("userCode"+user);
            return user.getUserCode();
        }
        return null;
    }

    public String getCurrentUserId() {
        UserInfoBySession user = getCurrentUser();
        System.out.println(user);
        if(user != null) {
            return user.getUserId();
        }
        return null;
    }


}
