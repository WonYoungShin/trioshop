package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFindPw {

    String userName;
    String userId;
    String userPasswd;

    public UserFindPw(String userName, String userId, String userPasswd) {
        this.userName = userName;
        this.userId = userId;
        this.userPasswd = userPasswd;
    }
}