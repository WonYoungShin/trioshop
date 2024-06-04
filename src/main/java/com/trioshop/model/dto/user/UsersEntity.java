package com.trioshop.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UsersEntity {
    private long userCode;
    private final long gradeCode;
    private final String userId;
    private String userPasswd;
    private String passwdSalt;

    public UsersEntity(long userCode, long gradeCode, String userId) {
        this.userCode = userCode;
        this.gradeCode = gradeCode;
        this.userId = userId;
    }
}
