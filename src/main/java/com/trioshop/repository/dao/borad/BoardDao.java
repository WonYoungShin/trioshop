package com.trioshop.repository.dao.borad;

import com.trioshop.model.dto.board.*;
import com.trioshop.repository.mybatis.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDao {
    private final BoardMapper boardMapper;

    public List<BoardCategoryEntity> categoryList(){
       return boardMapper.categoryList();
    }

    public List<BoardContentList> contentList(BoardCondition boardCondition){
        return boardMapper.contentList(boardCondition);
    }

    public void boardWrite(BoardWriteDTO boardWriteDTO) {
        boardMapper.boardWrite(boardWriteDTO);
    }

    public Long boardContentWrite(BoardWriteDTO boardWriteDTO){
        boardMapper.boardContentWrite(boardWriteDTO);
        return boardWriteDTO.getBoardCode();
    }

    public BoardContentDetailModel boardDetails(Long boardCode) {
        return boardMapper.boardDetails(boardCode);
    }

    public List<BoardContentDetailComment> boardDetailsCommentList(Long boardCode) {
        return boardMapper.boardDetailsCommentList(boardCode);
    }

    public void boardViewsIncrease(Long boardCode) {
        boardMapper.boardViewsIncrease(boardCode);
    }

    public void deleteContent(Long boardCode) {
        boardMapper.deleteContent(boardCode);
    }

    public BoardEditModel boardCurrentState(Long boardCode) {
        return boardMapper.boardCurrentState(boardCode);
    }

    public void boardEdit(BoardEditModel editModel) {
        boardMapper.boardEdit(editModel);
    }

    public void boardContentEdit(BoardEditModel editModel) {
        boardMapper.boardContentEdit(editModel);
    }
}
