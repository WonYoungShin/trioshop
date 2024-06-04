package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class PurchaseListModel {
    private final Long purchaseCode;
    private final Long itemCode;
    private final Integer purchaseQty;
    private final String factoryCode;
    private final String itemName;
    private final Integer purchasePrice;
    private final String categoryName;
    private final String itemSize;
    private final String itemColor;
    private final String categoryCode;
}
