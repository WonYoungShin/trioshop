package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ItemQtyModel {
    private Long itemCode;
    private Integer stockQty;

    public void setStockQty(Integer stockQty) {
        this.stockQty = stockQty;
    }
}
