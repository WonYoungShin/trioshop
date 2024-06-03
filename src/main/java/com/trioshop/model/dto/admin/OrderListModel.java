package com.trioshop.model.dto.admin;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class OrderListModel {
    private final String orderCode;
    private final Long userCode;
    private final String orderDate;
    private final String itemNames;
    private final String orderQtys;
    private final String statusName;
    private final String statusCode;
    private final String itemPrices;
    private final String deliveryCode;
    private final String waybillNum;
}
