package com.trioshop.model.dto.item;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCodeAndQty {
    private long itemCode;
    private long orderQty;

}
