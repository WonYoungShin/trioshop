package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PasswordChangeCodeAndStatus {
    private final Long userCode;
    private final Boolean status;
}
