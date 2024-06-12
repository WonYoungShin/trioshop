package com.trioshop.model.dto.popup;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PopupItemModel {
    private final String itemCode;
    private final String itemName;
    private final String categoryCode;
    private final String factoryCode;
    private final String itemSize;
    private final String itemColor;
}
