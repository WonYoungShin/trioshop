package com.trioshop.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UsersInfoEntity {
    private final long userCode;
    private final String userName;
    private String userAddress;
    private final String userTel;
    private final String userNickname;
}
