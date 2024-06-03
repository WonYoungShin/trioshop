package com.trioshop.model.dto.admin;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class PurchaseItemModel{
    private final Long itemCode;
    private final Integer purchasePrice;
    private final Integer purchaseQty;
}
