package com.trioshop.model.dto.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemInfo {

    private int itemCode;
    private String categoryId;
    private String factoryCode;
    private String itemName;
    private int itemPrice;
    private String itemSrc;

}
