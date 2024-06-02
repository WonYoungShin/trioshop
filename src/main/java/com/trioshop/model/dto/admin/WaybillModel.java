package com.trioshop.model.dto.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class WaybillModel {
    private final String deliveryCode;
    private final String waybillNum;
    private final String orderCode;  // orderCode 필드 추가
}
