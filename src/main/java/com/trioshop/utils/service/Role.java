package com.trioshop.utils.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "일반사용자"),
//    USER("ROLE_USER", "일반사용자"), 비회원
//    USER("ROLE_USER", "일반사용자"), 우수사용자
//    USER("ROLE_USER", "일반사용자"), 최우수사용자 같이 설정
ADMIN("ROLE_ADMIN", "일반관리자");

    private final String key;
    private final String title;
}
