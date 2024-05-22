package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockModel {
    private Long itemCode;
    private String itemName;
    private String categoryName;
    private Integer itemPrice;
    private Integer stockQty;
    private String factoryCode;
    private String itemSize;
    private String itemColor;
}
