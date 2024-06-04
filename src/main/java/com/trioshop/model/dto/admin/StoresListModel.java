package com.trioshop.model.dto.admin;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class StoresListModel {
    private final Long storeCode;
    private final Integer storesQty;
    private final Long purchaseCode;
    private final Long itemCode;
    private final String factoryCode;
    private final String itemName;
    private final String categoryName;
    private final Integer storesPrice;
    private final String itemSize;
    private final String itemColor;
}
