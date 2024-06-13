package com.trioshop.model.dto.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BoardContentDetailModelAndComment {
    private final BoardContentDetailModel content;
    private final List<BoardCommentSelectModel> comment;
}
