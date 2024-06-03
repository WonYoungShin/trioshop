package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateItemModel {
    private final Long itemCode;
    private final String itemName;
    private final String factoryCode;
    private final String itemColor;
}
