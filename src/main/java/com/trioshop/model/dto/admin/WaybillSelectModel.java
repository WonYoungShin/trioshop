package com.trioshop.model.dto.admin;

import lombok.Getter;

@Getter
public class WaybillSelectModel {
    String deliveryName;
    String waybillNum;
    String orderCode;  // orderCode 필드 추가
}
