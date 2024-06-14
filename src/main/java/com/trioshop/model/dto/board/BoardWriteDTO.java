package com.trioshop.model.dto.board;

import lombok.Getter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@ToString
public class BoardWriteDTO {
    private final String categoryCode;
    private final String boardTitle;
    private final String boardContent;
    private final String userCode;
    private String boardDate;
    private Long boardCode;

    public BoardWriteDTO(String categoryCode, String boardTitle, String boardContent, String userCode) {
        this.categoryCode = categoryCode;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.userCode = userCode;
        dateForCurrentDate();
    }
    private void dateForCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd hh:mm");
        this.boardDate = formatter.format(new Date());
    }
}
