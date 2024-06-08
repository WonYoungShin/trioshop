package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HeaderModel {
    private final String userNickname;
    private final Long gradeCode;
}
