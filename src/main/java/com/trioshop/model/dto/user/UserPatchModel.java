package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserPatchModel {
    private final String userAddress;
    private final String userTel;
    private final String userNickname;
}
