package com.trioshop.model.dto.admin;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddItemModel{
    private final String itemName;
    private final String categoryCode;
    private final String factoryCode;
    private final String itemSize;
    private final String itemColor;
    private final Integer itemPrice;

    private Long itemCode;

}
