package com.trioshop.model.dto.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardContentDetailComment {
    private final Long commentCode;
    private final Long userCode;
    private final String userName;
    private final String commentContent;
    private final String commentDate;
}
