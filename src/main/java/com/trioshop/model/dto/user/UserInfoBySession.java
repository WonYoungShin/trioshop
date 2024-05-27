package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserInfoBySession {
    long userCode;
    long gradeCode;
    String userId;
    String userNickname;



    @Override
    public String toString() {
        return "UserInfoBySession{" +
                "userCode=" + userCode +
                ", gradeCode=" + gradeCode +
                ", userId='" + userId + '\'' +
                ", userNickname='" + userNickname + '\'' +
                '}';
    }

}
