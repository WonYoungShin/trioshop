package com.trioshop.model.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoin {
    private Long userCode;
    private String userId;
    private String userPasswd;
    private String userName;
    private String userAddress;
    private String userTel;
    private String userNickname;
    private Long gradeCode = 1L;

    public UserJoin(String userId, String userPasswd, String userName, String userAddress, String userTel, String userNickname) {
        this.userId = userId;
        this.userPasswd = userPasswd;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userTel = userTel;
        this.userNickname = userNickname;
    }

    @Override
    public String toString() {
        return "UserJoin{" +
                "userCode=" + userCode +
                ", userId='" + userId + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", gradeCode=" + gradeCode +
                '}';
    }
}
