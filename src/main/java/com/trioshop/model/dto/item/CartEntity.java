package com.trioshop.model.dto.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartEntity {
    private String cartCode;
    private long userCode;
    private long itemCode;
    private long cartItemQty;

    public CartEntity() {
    }
    public CartEntity(long userCode, long itemCode, long cartItemQty) {
        this.userCode = userCode;
        this.itemCode = itemCode;
        this.cartItemQty = cartItemQty;
    }
    public CartEntity(long userCode, long itemCode) {
        this.userCode = userCode;
        this.itemCode = itemCode;
    }
}
