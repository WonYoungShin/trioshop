package com.trioshop.model.dto.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentEditModel {
    private final Long commentCode;
    private final String commentContent;
    private final Long boardCode;
}
