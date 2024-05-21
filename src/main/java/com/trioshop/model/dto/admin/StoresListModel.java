package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StoresListModel {
    Long storeCode;
    Integer storesQty;
    Long purchaseCode;
    Long itemCode;
    String factoryCode;
    String itemName;
    String categoryName;
    Integer storesPrice;
    String itemSize;
    String itemColor;
}
