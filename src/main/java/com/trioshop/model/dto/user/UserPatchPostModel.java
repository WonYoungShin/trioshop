package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserPatchPostModel{
    private final Long userCode;
    private final String userAddress;
    private final String userTel;
    private final String userNickname;

    public UserPatchPostModel(Long userCode, UserPatchModel userPatchModel) {
        this.userCode = userCode;
        this.userAddress = userPatchModel.getUserAddress();
        this.userTel = userPatchModel.getUserTel();
        this.userNickname = userPatchModel.getUserNickname();
    }
}
