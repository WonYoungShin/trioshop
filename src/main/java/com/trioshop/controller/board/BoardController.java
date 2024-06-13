package com.trioshop.controller.board;

import com.github.pagehelper.PageInfo;
import com.trioshop.model.dto.board.*;
import com.trioshop.service.board.BoardService;
import com.trioshop.utils.business.CategoryList;
import com.trioshop.utils.service.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CategoryList categoryList;
    private final SecurityUtils securityUtils;

    @GetMapping
    public String boardListPage(@ModelAttribute BoardCondition boardCondition, @RequestParam(value = "page", defaultValue = "1")int page, Model model){
        PageInfo<BoardContentList> boardContentListPageInfo = boardService.contentList(boardCondition, page);
        model.addAttribute("contentList", boardContentListPageInfo.getList());
        model.addAttribute("totalPages", boardContentListPageInfo.getPages());
        model.addAttribute("categoryList",categoryList.getBoardCategoryList());
        //댓글수 추가
        return "board/boardList";
    }

    @GetMapping("/write")
    public String boardWritePage(Model model){
        model.addAttribute("categoryList",categoryList.getBoardCategoryList());

        return "board/boardWrite";
    }

    @PostMapping("/write")
    public String boardWrite(@ModelAttribute BoardWriteDTO boardWriteDTO){
        Long boardCode = boardService.boardWrite(boardWriteDTO);
        return "redirect:/board/" + boardCode;
    }

    @GetMapping("/{boardCode}")
    public String boardDetailPage(@PathVariable Long boardCode, Model model){
        BoardContentDetailModelAndComment boardDetail = boardService.boardDetails(boardCode,securityUtils.getCurrentUserCode());
        model.addAttribute("boardDetailModel", boardDetail.getContent());
        model.addAttribute("commentList",boardDetail.getComment());
        return "board/boardDetail";
    }

    @DeleteMapping("/{boardCode}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long boardCode) {
        boardService.deleteContent(boardCode);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{boardCode}/edit")
    public String boardEditPage(@PathVariable Long boardCode,Model model){
        model.addAttribute("boardEdit", boardService.boardCurrentState(boardCode));
        model.addAttribute("categoryList",categoryList.getBoardCategoryList());
        return "board/boardEdit";
    }

    @PostMapping("/{boardCode}/edit")
    public String boardEdit(@ModelAttribute BoardEditModel editModel){
        boardService.boardEdit(editModel);
        return "redirect:/board/" + editModel.getBoardCode();
    }

    @PostMapping("/comment")
    public String boardCommentAdd(@ModelAttribute CommentAddModel commentAddModel){
        boardService.boardCommentAdd(commentAddModel);
        return "redirect:/board/" + commentAddModel.getBoardCode();
    }

    @PostMapping("/comment/{commentCode}")
    public String boardCommentEdit(@ModelAttribute CommentEditModel commentEditModel){
        boardService.commentEdit(commentEditModel);
        return "redirect:/board/" + commentEditModel.getBoardCode();
    }
    
    @DeleteMapping("/comment/{commentCode}")
    public ResponseEntity<Void> boardCommentDelete(@PathVariable Long commentCode) {
        boardService.commentDelete(commentCode);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comment/reply")
    public String commentReplyAdd(@ModelAttribute CommentReplyAddModel replyAddModel){
        boardService.commentReplyAdd(replyAddModel);
        return "redirect:/board/" + replyAddModel.getBoardCode();
    }



}
