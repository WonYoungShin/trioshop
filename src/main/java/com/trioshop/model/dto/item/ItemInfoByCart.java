package com.trioshop.model.dto.item;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemInfoByCart {
    private long cartCode;
    private long userCode;
    private long cartItemQty;
    private long itemCode;
    private String itemName;
    private long itemPrice;
    private String itemSrc;
    private String itemColor;
    private String itemSize;
    private long stockQty;
    private String categoryName;
}
