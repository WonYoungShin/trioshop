package com.trioshop.model.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UserIdPasswordLoginJoinModel {
    private final String userId;
    private final String userPasswd;
    private Long userCode;

    @Builder
    public UserIdPasswordLoginJoinModel(String userId, String userPasswd) {
        this.userId = userId;
        this.userPasswd = userPasswd;
    }
}
