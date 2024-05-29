package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GuestUserJoin2 {
    private long userCode;
    private long gradeCode;
    private String userName;
    private String userAddress;
    private String userTel;
    private String userNickname;

    public GuestUserJoin2(String userName, String userAddress, String userTel,String userNickname) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.userTel = userTel;
        this.userNickname = userNickname;
    }
}



