package com.trioshop.utils;

import com.trioshop.model.dto.item.CategoryEntity;
import com.trioshop.service.item.ItemService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class CategoryList {

    private final List<CategoryEntity> categoryList;
    public CategoryList(ItemService itemService) {
        this.categoryList = itemService.categoryList();
    }
}