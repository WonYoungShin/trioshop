package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockModel {
    Long itemCode;
    String itemName;
    String categoryName;
    Integer itemPrice;
    Integer stockQty;
    String factoryCode;
    String itemSize;
    String itemColor;
}
