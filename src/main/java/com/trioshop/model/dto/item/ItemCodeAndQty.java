package com.trioshop.model.dto.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemCodeAndQty {
    private long itemCode;
    private long orderQty;

    public ItemCodeAndQty(long itemCode, long orderQty) {
        this.itemCode = itemCode;
        this.orderQty = orderQty;
    }
}
