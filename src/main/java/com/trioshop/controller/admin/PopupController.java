package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.FactoryCondition;
import com.trioshop.model.dto.admin.FactoryEntity;
import com.trioshop.model.dto.admin.PurchaseListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.model.dto.popup.PopupItemModel;
import com.trioshop.service.admin.PopupService;
import com.trioshop.service.admin.PurchaseService;
import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PopupController {
    private final PopupService popupService;
    private final PurchaseService purchaseService;
    private final CategoryList categoryList;

    @GetMapping("/popupItemList")
    public String popupItemList(@ModelAttribute ItemCondition itemCondition, Model model) {
        List<PopupItemModel> popupItemList = popupService.findByAll(itemCondition);
        model.addAttribute("categoryList", categoryList.getCategoryList());
        model.addAttribute("itemList", popupItemList);
        return "/admin/popupItemList";
    }
    @GetMapping("/popupFactoryList")
    public String popupFactoryList(@ModelAttribute FactoryCondition factoryCondition, Model model) {
        List<FactoryEntity> popupFactoryList = popupService.factoryFindByAll(factoryCondition);
        model.addAttribute("factoryList", popupFactoryList);
        return "/admin/popupFactoryList";
    }
    @GetMapping("/popupPurchaseList")
    public String popupPurchaseList(@ModelAttribute ItemCondition itemCondition, Model model) {
        List<PurchaseListModel> popupPurchaseList = purchaseService.findAll(itemCondition);
        model.addAttribute("categoryList", categoryList.getCategoryList());
        model.addAttribute("purchaseList", popupPurchaseList);
        return "/admin/popupPurchaseList";
    }
}

