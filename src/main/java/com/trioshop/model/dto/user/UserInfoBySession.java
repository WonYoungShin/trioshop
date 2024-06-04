package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class UserInfoBySession {
    private final Long userCode;
    private final Long gradeCode;
    private final String userId;
    private final String userNickname;
}
