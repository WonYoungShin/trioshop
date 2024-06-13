package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class PasswordCheckedModel {

    private final String newPassword;
    private final String confirmPassword;

}
