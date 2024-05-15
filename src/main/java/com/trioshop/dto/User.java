package com.trioshop.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    int userCode;
    int gradeCode;
    String userId;
    String userPasswd;
    String userName;
    String userAddress;
    String userTel;
    String userSex;
    String userNicename;

    @Override
    public String toString() {
        return "User{" +
                "userCode=" + userCode +
                ", gradeCode=" + gradeCode +
                ", userId='" + userId + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userNicename='" + userNicename + '\'' +
                '}';
    }
}
