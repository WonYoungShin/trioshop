package com.trioshop.model.dto.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardContentDetailModel {
    private final Long boardCode;
    private final String categoryName;
    private final String boardTitle;
    private final String userCode;
    private final String userName;
    private final String boardDate;
    private final String boardViews;
    private final String boardContent;
}
