package com.trioshop.model.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoin {
    private Long userCode;
    private String userId;
    private String userPasswd;
    private String userName;
    private String userAddress;
    private String userTel;
    private String userNickname;

    // 생성자를 간소화한 람다 표현식
    public UserJoin(String userId, String userPasswd, String userName, String userAddress, String userTel, String userNickname) {
        this(null, userId, userPasswd, userName, userAddress, userTel, userNickname);
    }
}
