package com.trioshop.model.dto.item;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemInfoByUser {
    // 유저에게 보여질 제품정보
    private long itemCode;
    private String categoryName;
    private String factoryName;
    private String itemName;
    private long stockQty;
    private long orderQty;
    private long itemPrice;
    private String itemSrc;
    private String itemColor;
    private String itemSize;

    public void setOrderQty(long orderQty) {
        this.orderQty = orderQty;
    }
}
