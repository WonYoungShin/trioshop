package com.trioshop.model.dto.admin;

import lombok.Getter;

@Getter
public class UpdateItemModel {
    private Long itemCode;
    private String itemName;
    private String factoryCode;
    private String itemColor;

    public UpdateItemModel() {
    }

    public UpdateItemModel(Long itemCode, String itemName, String factoryCode, String itemColor) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.factoryCode = factoryCode;
        this.itemColor = itemColor;
    }
}
