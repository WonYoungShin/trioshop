package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdPasswd {

    private String userId;
    private String userPasswd;

    public UserIdPasswd(String userId, String userPasswd) {
        this.userId = userId;
        this.userPasswd = userPasswd;
    }
}
