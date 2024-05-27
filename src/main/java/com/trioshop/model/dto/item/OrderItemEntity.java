package com.trioshop.model.dto.item;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity {
    private String orderCode;
    private long itemCode;
    private long orderQty;
}

