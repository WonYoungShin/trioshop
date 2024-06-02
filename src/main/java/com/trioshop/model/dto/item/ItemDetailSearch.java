package com.trioshop.model.dto.item;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailSearch {
    private long itemCode;
    private String itemName;
    private String itemColor;
    private String itemSize;
    private long orderQty;

    public ItemDetailSearch(long itemCode, String itemColor, String itemSize) {
        this.itemCode = itemCode;
        this.itemColor = itemColor;
        this.itemSize = itemSize;
    }

}
