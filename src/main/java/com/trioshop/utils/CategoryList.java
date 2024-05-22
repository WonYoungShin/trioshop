package com.trioshop.utils;

import com.trioshop.service.item.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class CategoryList {
    private final List<String> categoryList;

    public CategoryList(ItemService itemService) {
        this.categoryList = itemService.categoryList();
    }
}