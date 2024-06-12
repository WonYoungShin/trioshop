package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class HeaderModel {
    private final Long userCode;
    private final String userNickname;
    private final Long gradeCode;
}
