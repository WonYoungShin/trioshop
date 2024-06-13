package com.trioshop.model.dto.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardCommentSelectModel {
    private final Long commentCode;
    private final Long userCode;
    private final String userNickname;
    private final String commentContent;
    private final String commentDate;
    private final Integer level;

}
