package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StockModel {
    private final Long itemCode;
    private final String itemName;
    private final String categoryName;
    private final Integer itemPrice;
    private final Integer stockQty;
    private final String factoryCode;
    private final String itemSize;
    private final String itemColor;
}
