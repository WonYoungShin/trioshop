package com.trioshop.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Oauth2JoinModel {
    private final String userId;
    private final String gradeCode;
    private Long userCode;
}
