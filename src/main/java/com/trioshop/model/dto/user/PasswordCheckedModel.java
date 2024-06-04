package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class PasswordCheckedModel {
    @Getter
    private final String newPassword;
    private final String confirmPassword;


    public boolean checkingPassword() {
        return this.confirmPassword.equals(this.newPassword);
    }
}
