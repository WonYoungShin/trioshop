package com.trioshop.model.dto.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderItemInfo {

    private int itemCode;
    private String categoryName;
    private String factoryName;
    private String itemName;
    private int stockQty;
    private int itemPrice;
    private String itemSrc;
}
