package com.trioshop.controller.item;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.item.*;
import com.trioshop.model.dto.user.UserAddressInfo;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.service.item.ItemService;
import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemInfoController {
    private final ItemService itemService;
    //카테고리 목록 싱글톤으로 관리
    private final CategoryList categoryList;
    @GetMapping("/itemList") // 전체아이템 목록화면
    public String itemListPage(Model model) {
        List<ItemInfoByUser> itemList = itemService.findAllItem();
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
        ItemInfoByUser item = itemService.itemInfoByCode(itemCode);

        List<ItemDetailSearch> itemLists = itemService.itemDetailNameSearch(item.getItemName());
        model.addAttribute("itemLists", itemLists);

        model.addAttribute("item", item);
        return "user/itemInfo/itemPage";
    }
}

