package com.trioshop.model.dto.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardCategoryEntity {
    private final String categoryCode;
    private final String categoryName;

}
