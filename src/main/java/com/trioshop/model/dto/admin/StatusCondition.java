package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StatusCondition {
    private final String userCode;
    private final String statusCode;
}
