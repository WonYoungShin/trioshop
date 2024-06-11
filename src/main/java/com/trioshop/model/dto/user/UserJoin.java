package com.trioshop.model.dto.user;

import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
public class UserJoin {
    private final String userId;
    private final String userPasswd;
    private final String userName;
    private final String userAddress;
    private final String userTel;
    private final String userNickname;
    private final Long gradeCode = 1L;
}
