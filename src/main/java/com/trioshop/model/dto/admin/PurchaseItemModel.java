package com.trioshop.model.dto.admin;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PurchaseItemModel{
    private Long purchaseCode;
    private Long itemCode;
    private Integer purchasePrice;
    private Integer purchaseQty;


    public PurchaseItemModel() {

    }

    public PurchaseItemModel(Long purchaseCode, Long itemCode, Integer purchaseQty) {
        this(itemCode,purchaseCode,purchaseQty,0);
    }

    public PurchaseItemModel(Long purchaseCode, Long itemCode, Integer purchaseQty, Integer purchasePrice) {
        this.itemCode = itemCode;
        this.purchaseCode = purchaseCode;
        this.purchaseQty = purchaseQty;
        this.purchasePrice = purchasePrice;
    }

    @Override
    public String toString() {
        return "PurchaseItemModel{" +
                "purchaseCode='" + purchaseCode + '\'' +
                ", purchaseQty=" + purchaseQty +
                ", itemCode=" + itemCode +
                ", purchasePrice=" + purchasePrice +
                '}';
    }
}
