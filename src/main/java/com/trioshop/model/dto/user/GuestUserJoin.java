package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GuestUserJoin {
    long userCode;
    long gradeCode;
    private String userName;
    private String userAddress;
    private String userTel;

    public GuestUserJoin(long userCode, long gradeCode, String userName, String userAddress, String userTel) {
        this.userCode = userCode;
        this.gradeCode = gradeCode;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userTel = userTel;
    }

    @Override
    public String toString() {
        return "GuestUserJoin{" +
                "userCode=" + userCode +
                ", gradeCode=" + gradeCode +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userTel='" + userTel + '\'' +
                '}';
    }
}
