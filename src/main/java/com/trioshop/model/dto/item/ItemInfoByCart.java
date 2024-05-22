package com.trioshop.model.dto.item;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemInfoByCart {
    long cartCode;
    long userCode;
    long cartItemQty;
    long itemCode;
    String itemName;
    long itemPrice;
    String itemSrc;
    String itemColor;
    String itemSize;
    long stockQty;
    String categoryName;
}
