package com.trioshop.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserPatch {
     long userCode;
     String userPasswd;
     String userName;
     String userAddress;
     String userTel;
     String userNickname;

    public UserPatch() {
        // 기본 생성자 추가
    }

    @Override
    public String toString() {
        return "UserPatch{" +
                "userCode=" + userCode +
                ", userPasswd='" + userPasswd + '\'' +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userNickname='" + userNickname + '\'' +
                '}';
    }
}
