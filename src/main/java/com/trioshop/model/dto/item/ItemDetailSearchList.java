package com.trioshop.model.dto.item;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailSearchList {
    private List<ItemDetailSearch> itemDetailSearchList;
}
