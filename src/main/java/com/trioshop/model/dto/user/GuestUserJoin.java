package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class GuestUserJoin {
    private long userCode;
    private long gradeCode;
    private String userName;
    private String userAddress;
    private String userTel;
    private String userNickname;
}
