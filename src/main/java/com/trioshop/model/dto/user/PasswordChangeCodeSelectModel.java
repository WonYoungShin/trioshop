package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PasswordChangeCodeSelectModel {
    private final String userName;
    private final String userId;
}