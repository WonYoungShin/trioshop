package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.*;
import com.trioshop.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/trioAdmin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public String index() {
        return "/admin/adminMain";
    }

    @GetMapping("/addItem")
    public String add(){
        return "/admin/addItem";
    }

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute AddItemModel itemModel, RedirectAttributes redirectAttributes){
        log.info("Received itemModel: " + itemModel.toString());

        AddItemModel saveItemModel = adminService.itemSave(itemModel);
        log.info("Saved itemModel: " + saveItemModel.toString());
        return "redirect:/trioAdmin";
    }


    @GetMapping("/purchase")
    public String purchase(){
        return "/admin/purchase";
    }

    @PostMapping("/purchase")
    public String addPurchase(@ModelAttribute PurchaseItemModel itemModel, RedirectAttributes redirectAttributes){
        log.info("Received itemModel: " + itemModel.toString());
        PurchaseItemModel saveItemModel = adminService.purchaseSave(itemModel);
        log.info("Saved itemModel: " + saveItemModel.toString());
        return "redirect:/trioAdmin/purchaseList";
    }

    @GetMapping("/purchaseList")
    public String purchaseList(Model model){
        List<PurchaseListModel> purchaseList = adminService.purchaseFindAll();
        model.addAttribute("purchaseList", purchaseList);
        return "/admin/purchaseList";
    }


    @GetMapping("/stores")
    public String stores(){
        return "/admin/stores";
    }

    @PostMapping("/stores")
    public String addStores(@ModelAttribute StoreItemModel itemModel, RedirectAttributes redirectAttributes){
        log.info("Received itemModel: " + itemModel.toString());
        StoreItemModel saveItemModel = adminService.storeSave(itemModel);
        log.info("Saved itemModel: " + saveItemModel.toString());
        return "redirect:/trioAdmin/storesList";
    }
    @GetMapping("/storesList")
    public String storesList(Model model){
        List<StoresListModel> storesList = adminService.storesFindAll();
        model.addAttribute("storesList", storesList);
        return "/admin/storesList";
    }
    @GetMapping("/chart")
    public String chart(){
        return "/admin/chart";
    }

    @GetMapping("/stock")
    public String stock(Model model){
        List<StockModel> stockList = adminService.stockFindAll();
        model.addAttribute("stockList", stockList);
        return "/admin/stock";
    }

}
