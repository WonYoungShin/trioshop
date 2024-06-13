package com.trioshop.controller;

import com.github.pagehelper.PageInfo;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.service.item.ItemService;
import com.trioshop.utils.business.CategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomepageController {
    private final ItemService itemService;
    private final CategoryList categoryList;

    @GetMapping("/") // 홈화면
    public String userList(@ModelAttribute ItemCondition itemCondition,
                           @RequestParam(defaultValue = "1") int page,
                           Model model) {
        PageInfo<ItemInfoByUser> itemListPageInfo = itemService.searchItems(itemCondition,page);
        model.addAttribute("itemList", itemListPageInfo.getList());
        //카테고리 목록 불러오기
        model.addAttribute("categoryList", categoryList.getCategoryList());
        model.addAttribute("totalPages", itemListPageInfo.getPages());

        return "etc/homePage";
    }
}
