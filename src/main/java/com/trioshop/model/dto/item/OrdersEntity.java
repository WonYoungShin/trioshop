package com.trioshop.model.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class OrdersEntity {

    private String orderCode;
    private long userCode;
    private String orderReceiver;
    private String orderDestination;
    private String orderDate;
    private String orderTel;
    private String statusCode;
}
//  private long orderItemCode;
//    private String itemName;
//    private long itemPrice;
//    private String itemSrc;
//    private long stockQty;

