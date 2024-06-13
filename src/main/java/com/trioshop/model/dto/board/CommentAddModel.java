package com.trioshop.model.dto.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class CommentAddModel {
    private final Long boardCode;
    private final String commentContent;
    private final Long userCode;
    private String commentDate;
    private Long commentCode;

    public CommentAddModel(Long boardCode, String commentContent, Long userCode) {
        this.boardCode = boardCode;
        this.commentContent = commentContent;
        this.userCode = userCode;
        dateForCurrentDate();
    }

    private void dateForCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd a h시 mm분");
        this.commentDate = formatter.format(new Date());
    }
}
