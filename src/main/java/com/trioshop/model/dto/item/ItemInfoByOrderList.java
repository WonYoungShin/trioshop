package com.trioshop.model.dto.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemInfoByOrderList {
    private String orderCode;
    private String orderDate;
    private String statusName;
    private Long itemCode;
    private String itemName;
    private long orderQty;
    private long itemPrice;
}
