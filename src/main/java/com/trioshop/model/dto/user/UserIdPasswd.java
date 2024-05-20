package com.trioshop.model.dto.user;

import lombok.Data;

@Data// getter, setter, 생성자
public class UserIdPasswd {
    private String userId;
    private String userPasswd;

    public UserIdPasswd(String userId, String userPasswd) {
        this.userId = userId;
        this.userPasswd = userPasswd;
    }
}
