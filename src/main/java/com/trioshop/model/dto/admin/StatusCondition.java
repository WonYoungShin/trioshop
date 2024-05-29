package com.trioshop.model.dto.admin;

import lombok.Getter;

@Getter
public class StatusCondition {
    private String userCode;
    private String statusCode;

    public StatusCondition(String userCode, String deliveryCode) {
        this.userCode = userCode;
        this.statusCode = deliveryCode;
    }
}
