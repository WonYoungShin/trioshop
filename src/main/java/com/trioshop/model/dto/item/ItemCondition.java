package com.trioshop.model.dto.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class ItemCondition {
    private final String itemName;
    private final String category;
}
