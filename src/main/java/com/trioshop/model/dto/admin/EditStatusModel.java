package com.trioshop.model.dto.admin;

import lombok.Getter;

@Getter
public class EditStatusModel {
    String orderCode;
    String statusCode;

    public EditStatusModel(String orderCode, String statusCode) {
        this.orderCode = orderCode;
        this.statusCode = statusCode;
    }
}
