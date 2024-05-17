package com.trioshop.model.dto.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemInfoByUser {
    // 유저에게 보여질 제품정보
    private int itemCode;
    private String categoryName;
    private String factoryName;
    private String itemName;
    private int stockQty;
    private int itemPrice;
    private String itemSrc;

}
