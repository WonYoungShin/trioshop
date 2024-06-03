package com.trioshop.model.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class LoginModel {
    private final String userId;
    private final String userPasswd;
}