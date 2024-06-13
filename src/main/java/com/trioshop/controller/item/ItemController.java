package com.trioshop.controller.item;

import com.github.pagehelper.PageInfo;
import com.trioshop.model.dto.item.*;
import com.trioshop.service.item.ItemService;
import com.trioshop.utils.business.CategoryList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;
    //카테고리 목록 싱글톤으로 관리
    private final CategoryList categoryList;
    @GetMapping("/itemList") // 전체아이템 목록화면
    public String itemListPage(@ModelAttribute ItemCondition itemCondition,
                               @RequestParam(defaultValue = "1") int page,
                               Model model) {
        PageInfo<ItemInfoByUser> itemListPageInfo = itemService.searchItems(itemCondition, page);
        model.addAttribute("itemList", itemListPageInfo.getList());
        model.addAttribute("categoryList", categoryList.getCategoryList());
        model.addAttribute("totalPages", itemListPageInfo.getPages());
        return "user/itemInfo/itemList";
    }

    @GetMapping("/item/{itemCode}") //아이템 상세 페이지로
    public String itemDetailPage(@PathVariable("itemCode") long itemCode,
                                 Model model) {
        // 상품의 상세 정보를 찾아 반환
        ItemInfoByUser item = itemService.itemInfoByCode(itemCode);
        model.addAttribute("item", item);

        // 이름이 같은 다른 사이즈, 색의 제품을 검색 하여 반환
        List<ItemDetailSearch> itemLists = itemService.itemDetailNameSearch(item.getItemName());
        model.addAttribute("itemLists", itemLists);

        return "user/itemInfo/itemPage";
    }
}

