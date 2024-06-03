package com.trioshop.controller;

import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.service.item.ItemService;
import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomepageController {
    private final ItemService itemService;
    private final CategoryList categoryList;

    @GetMapping("/") // 홈화면
    public String userList(Model model) {
        List<ItemInfoByUser> itemList = itemService.findAllItem();
        model.addAttribute("itemList", itemList);
        //카테고리 목록 불러오기
        model.addAttribute("categoryList", categoryList.getCategoryList());
        return "etc/homePage";
    }
}
