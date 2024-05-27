package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoin {
    private Long userCode;
    private String userId;
    private String userPasswd;
    private String userName;
    private String userAddress;
    private String userTel;
    private String userNickname;

    public UserJoin(String userId, String userPasswd, String userName, String userAddress, String userTel, String userNickname) {
        this.userCode = null;
        this.userId = userId;
        this.userPasswd = userPasswd;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userTel = userTel;
        this.userNickname = userNickname;
    }
}
