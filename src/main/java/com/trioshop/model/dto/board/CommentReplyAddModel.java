package com.trioshop.model.dto.board;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class CommentReplyAddModel {
    private final Long boardCode;
    private final Long replyCode;
    private final Long userCode;
    private final String commentContent;
    private String commentDate;
    private Long commentCode;

    public CommentReplyAddModel(Long boardCode, Long replyCode, Long userCode, String commentContent) {
        this.boardCode = boardCode;
        this.replyCode = replyCode;
        this.userCode = userCode;
        this.commentContent = commentContent;
        dateForCurrentDate();
    }
    private void dateForCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd hh:mm");
        this.commentDate = formatter.format(new Date());
    }
}
