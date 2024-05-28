package com.trioshop.model.dto.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class WaybillModel {
    String deliveryCode;
    String waybillNum;
    String orderCode;  // orderCode 필드 추가

    @Override
    public String toString() {
        return "WaybillModel{" +
                "deliveryCode='" + deliveryCode + '\'' +
                ", waybillNum='" + waybillNum + '\'' +
                ", orderCode='" + orderCode + '\'' +
                '}';
    }
}
