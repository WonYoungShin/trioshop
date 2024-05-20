package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StoreItemModel {
    Long itemCode;
    Long storeCode;
    Integer storeQty;
    Long purchaseCode;
    Integer storePrice;

    public StoreItemModel() {
    }

    public StoreItemModel(Long storeCode, Integer storeQty, Long purchaseCode, Long itemCode, Integer stockPrice) {
        this.itemCode = itemCode;
        this.storeCode = storeCode;
        this.storeQty = storeQty;
        this.purchaseCode = purchaseCode;

        this.storePrice = stockPrice;
    }
    public StoreItemModel(Long storeCode, Integer storeQty, Long itemCode, Integer stockPrice) {
        this.itemCode = itemCode;
        this.storeCode = storeCode;
        this.storeQty = storeQty;
        this.storePrice = stockPrice;
    }

    @Override
    public String toString() {
        return "StoreItemModel{" +
                "storeCode='" + storeCode + '\'' +
                ", storeQty=" + storeQty +
                ", purchaseCode='" + purchaseCode + '\'' +
                ", stockPrice=" + storePrice +
                ", itemCode=" + itemCode +
                '}';
    }
}
