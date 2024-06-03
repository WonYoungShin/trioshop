package com.trioshop.model.dto.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ItemDetailSearchList {
    private List<ItemDetailSearch> itemDetailSearchList;
}
