package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ItemCodeAdjustModel {
    private final Long itemCode;
    private final Integer quantity;
}
