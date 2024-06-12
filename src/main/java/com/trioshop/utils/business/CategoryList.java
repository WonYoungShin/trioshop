package com.trioshop.utils.business;

import com.trioshop.model.dto.board.BoardCategoryEntity;
import com.trioshop.model.dto.item.CategoryEntity;
import com.trioshop.service.board.BoardService;
import com.trioshop.service.item.ItemService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class CategoryList {

    private final List<CategoryEntity> categoryList;
    private final List<BoardCategoryEntity> boardCategoryList;

    public CategoryList(ItemService itemService, BoardService boardService) {
        this.categoryList = itemService.categoryList();
        this.boardCategoryList = boardService.categoryList();
    }
}