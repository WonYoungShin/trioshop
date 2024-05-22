package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPatch {
    private Long userCode;
    private String userPasswd;
    private String userName;
    private String userAddress;
    private String userTel;
    private String userNickname;

    public UserPatch() {

    }

    public UserPatch(Long userCode, String userPasswd, String userName, String userAddress, String userTel, String userNickname) {
        this.userCode = userCode;
        this.userPasswd = userPasswd;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userTel = userTel;
        this.userNickname = userNickname;
    }

}
