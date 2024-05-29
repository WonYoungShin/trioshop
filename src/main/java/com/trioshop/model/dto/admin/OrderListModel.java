package com.trioshop.model.dto.admin;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderListModel {
    private String orderCode;
    private Long userCode;
    private String orderDate;
    private String itemNames;
    private String orderQtys;
    private String statusName;
    private String statusCode;
    private String itemPrices;
    private String deliveryCode;
    private String waybillNum;
}
