package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFindPw {
    private String userName;
    private String userId;

    public UserFindPw(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }
}
