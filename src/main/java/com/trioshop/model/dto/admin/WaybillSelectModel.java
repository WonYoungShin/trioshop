package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WaybillSelectModel {
    private final String deliveryName;
    private final String waybillNum;
}
