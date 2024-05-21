package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PurchaseListModel {
    Long purchaseCode;
    Long itemCode;
    Integer purchaseQty;
    String factoryCode;
    String itemName;
    String categoryName;
    String itemSize;
    String itemColor;
}
