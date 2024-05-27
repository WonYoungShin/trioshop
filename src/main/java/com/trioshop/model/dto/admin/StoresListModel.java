package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StoresListModel {
    private Long storeCode;
    private Integer storesQty;
    private Long purchaseCode;
    private Long itemCode;
    private String factoryCode;
    private String itemName;
    private String categoryName;
    private Integer storesPrice;
    private String itemSize;
    private String itemColor;
}
