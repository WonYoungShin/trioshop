package com.trioshop.model.dto.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserInfoBySession {
    int userCode;
    int gradeCode;
    String userId;
    String userPasswd;
    String userName;
    String userAddress;
    String userTel;
    String userNickname;

    public UserInfoBySession(int gradeCode) {
        this.gradeCode = gradeCode;
    }
}
