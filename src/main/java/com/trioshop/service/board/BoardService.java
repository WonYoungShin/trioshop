package com.trioshop.service.board;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trioshop.exception.ApplicationException;
import com.trioshop.exception.ExceptionType;
import com.trioshop.model.dto.board.*;
import com.trioshop.repository.dao.borad.BoardDao;
import com.trioshop.repository.redis.RedisRepository;
import com.trioshop.utils.service.PagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardDao boardDao;
    private final PagingService pagingService;
    private final RedisRepository repository;

    public List<BoardCategoryEntity> categoryList(){
        return boardDao.categoryList();
    }

    public PageInfo<BoardContentList> contentList(BoardCondition boardCondition, int page){
        return pagingService.getPagedData(page, () -> boardDao.contentList(boardCondition));
    }


    @Transactional
    public Long boardWrite(BoardWriteDTO boardWriteDTO) {
        try{
            boardDao.boardWrite(boardWriteDTO);
            return boardDao.boardContentWrite(boardWriteDTO);
        }   catch (Exception e) {
            throw new ApplicationException(ExceptionType.DONT_SAVE_BOARD);
        }
    }
    @Transactional
    public BoardContentDetailModelAndComment boardDetails(Long boardCode,Long userCode) {
        if(repository.isCodeBoardView(userCode,boardCode)){
            repository.viewSave(userCode,boardCode);
            boardDao.boardViewsIncrease(boardCode);
        }

        BoardContentDetailModel content = boardDao.boardDetails(boardCode);

        List<BoardCommentSelectModel> comment = boardDao.boardDetailsCommentList(boardCode);



        return new BoardContentDetailModelAndComment(content,comment);
    }

    public void deleteContent(Long boardCode) {
        boardDao.deleteContent(boardCode);
    }

    public BoardEditModel boardCurrentState(Long boardCode) {
        return boardDao.boardCurrentState(boardCode);
    }

    @Transactional
    public void boardEdit(BoardEditModel editModel) {
        try{
            boardDao.boardEdit(editModel);
            boardDao.boardContentEdit(editModel);
        }   catch (Exception e) {
        throw new ApplicationException(ExceptionType.DONT_SAVE_BOARD);
        }
    }

    public void boardCommentAdd(CommentAddModel commentAddModel) {
        boardDao.boardCommentAdd(commentAddModel);
    }

    public void commentDelete(Long commentCode) {
        boardDao.commentDelete(commentCode);
    }

    public void commentEdit(CommentEditModel commentEditModel) {
        boardDao.commentEdit(commentEditModel);
    }

    public void commentReplyAdd(CommentReplyAddModel replyAddModel) {
        boardDao.commentReplyAdd(replyAddModel);
    }
}
