package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.board.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardCategoryEntity> categoryList();

    List<BoardContentList> contentList(BoardCondition boardCondition);

    void boardWrite(BoardWriteDTO boardWriteDTO);

    void boardContentWrite(BoardWriteDTO boardWriteDTO);

    BoardContentDetailModel boardDetails(Long boardCode);

    List<BoardContentDetailComment> boardDetailsCommentList(Long boardCode);

    void boardViewsIncrease(Long boardCode);

    void deleteContent(Long boardCode);

    BoardEditModel boardCurrentState(Long boardCode);

    void boardEdit(BoardEditModel editModel);

    void boardContentEdit(BoardEditModel editModel);
}
