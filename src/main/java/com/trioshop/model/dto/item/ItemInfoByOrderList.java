package com.trioshop.model.dto.item;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemInfoByOrderList {
    private String orderCode;
    private String orderDate;
    private String statusName;
    private Long itemCode;
    private String itemName;
    private long orderQty;
    private long itemPrice;
}
