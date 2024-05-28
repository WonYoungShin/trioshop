package com.trioshop.model.dto.item;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
@AllArgsConstructor
public class ItemDetailSearch {
    long itemCode;
    String itemName;
    String itemColor;
    String itemSize;
    long orderQty;

    public ItemDetailSearch() {
    }

    public ItemDetailSearch(long itemCode, String itemColor, String itemSize) {
        this.itemCode = itemCode;
        this.itemColor = itemColor;
        this.itemSize = itemSize;
    }

    @Override
    public String toString() {
        return "ItemDetailSearch{" +
                "itemCode=" + itemCode +
                ", itemName='" + itemName + '\'' +
                ", itemColor='" + itemColor + '\'' +
                ", itemSize='" + itemSize + '\'' +
                ", orderQty=" + orderQty +
                '}';
    }
}
