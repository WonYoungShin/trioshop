package com.trioshop.model.dto.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardCondition {
    private final String title;
    private final String category;
}
