package com.trioshop.model.dto.admin;


import lombok.Getter;

import java.util.Objects;

@Getter
public class StoreItemModel {
    private final Long itemCode;
    private final Long storeCode;
    private final Integer storeQty;
    private final Long purchaseCode;
    private final Integer storePrice;

    public StoreItemModel(Long itemCode, Long storeCode, Integer storeQty, Long purchaseCode, Integer storePrice) {
        this.itemCode = itemCode;
        this.storeCode = storeCode;
        this.storeQty = storeQty;

        if(Objects.nonNull(purchaseCode)) {
            this.purchaseCode = purchaseCode;
        }else{
            this.purchaseCode = 0L;
        }
        this.storePrice = storePrice;
    }
}
