package com.trioshop.model.dto.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommentAddModel {
    private final Long boardCode;
    private final String commentContent;
    private final Long userCode;
    private Long commentCode;
}
