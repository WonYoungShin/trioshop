package com.trioshop.controller.item;

import com.trioshop.model.dto.item.*;
import com.trioshop.service.item.ItemService;
import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    //카테고리 목록 싱글톤으로 관리
    private final CategoryList categoryList;
    @GetMapping("/itemList") // 전체아이템 목록화면
    public String itemListPage(Model model) {
        List<ItemInfoByUser> itemList = itemService.searchItems(null);
        model.addAttribute("itemList", itemList);
        model.addAttribute("categoryList", categoryList.getCategoryList());
        return "user/itemInfo/itemList";
    }
    @GetMapping("/searchItems") // 상품 검색 페이지로
    public String searchItems(@ModelAttribute ItemCondition itemCondition,
                              Model model) {

        List<ItemInfoByUser> itemList = itemService.searchItems(itemCondition);
        model.addAttribute("itemList", itemList);
        model.addAttribute("categoryList", categoryList.getCategoryList());
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

