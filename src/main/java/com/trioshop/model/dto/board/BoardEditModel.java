package com.trioshop.model.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class BoardEditModel {
    private final Long boardCode;
    private final String categoryCode;
    private final String boardTitle;
    private final String boardContent;
}
