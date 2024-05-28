package com.trioshop.model.dto.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class WaybillModel {
    String deliveryCode;
    String waybillNum;
    String orderCode;  // orderCode 필드 추가
}
