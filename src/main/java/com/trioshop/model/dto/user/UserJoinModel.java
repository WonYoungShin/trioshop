package com.trioshop.model.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserJoinModel {
    private final String userId;
    private final String userPasswd;
    private Long userCode;

    @Builder
    public UserJoinModel(String userId, String userPasswd) {
        this.userId = userId;
        this.userPasswd = userPasswd;
    }
}
