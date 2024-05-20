package com.trioshop.model.dto.admin;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PurchaseItemModel{
    Long purchaseCode;
    Long itemCode;
    Integer purchaseQty;


    public PurchaseItemModel() {

    }

    public PurchaseItemModel(Long purchaseCode, Long itemCode, Integer purchaseQty) {
        this.itemCode = itemCode;
        this.purchaseCode = purchaseCode;
        this.purchaseQty = purchaseQty;
    }

    @Override
    public String toString() {
        return "PurchaseItemModel{" +
                "purchaseCode='" + purchaseCode + '\'' +
                ", purchaseQty=" + purchaseQty +
                ", itemCode=" + itemCode +
                '}';
    }
}
