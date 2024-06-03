package com.trioshop.model.dto.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderListByUser {
    private final String orderCode;
    private final String orderDate;
    private final String itemNames;
    private final String orderQtys;
    private final String statusName;
    private final String statusCode;
    private final String itemPrices;
    private final String deliveryCode;
    private final String waybillNum;
}
