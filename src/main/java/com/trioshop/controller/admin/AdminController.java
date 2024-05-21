package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.*;
import com.trioshop.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;


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
        PurchaseItemModel saveItemModel = adminService.purchaseSave(itemModel);
        return "redirect:/trioAdmin/purchaseList";
    }

    @GetMapping("/purchaseList")
    public String purchaseList(Model model){
        List<PurchaseListModel> purchaseList = adminService.purchaseFindAll();
        model.addAttribute("purchaseList", purchaseList);
        return "/admin/purchaseList";
    }
    @GetMapping("/purchase/{purchaseCode}")
    public String purchaseDetail(@PathVariable("purchaseCode") Long purchaseCode, Model model){
        try {
            PurchaseListModel purchaseItem = adminService.purchaseFindByCode(purchaseCode).orElseThrow(NoSuchElementException::new);
            model.addAttribute("purchase", purchaseItem);
        } catch (NoSuchElementException e) {
            log.info("조회 실패");
            return "/admin/purchaseList";
        }

        return "/admin/purchaseDetail";
    }



    @GetMapping("/stores")
    public String stores(){
        return "/admin/stores";
    }

    @PostMapping("/stores")
    public String addStores(@ModelAttribute StoreItemModel itemModel, RedirectAttributes redirectAttributes){
        StoreItemModel saveItemModel = adminService.storeSave(itemModel);
        try {
            addStockQty(saveItemModel);
        } catch (NoSuchElementException e) {
            log.info("입고 처리실패 실패");
            return "/admin/stores";
        }
        return "redirect:/trioAdmin/storesList";
    }

    private void addStockQty(StoreItemModel saveItemModel) {
            AddItemQtyModel item = adminService.itemFindById(saveItemModel.getItemCode()).orElseThrow(NoSuchElementException::new);
            item.setStockQty(item.getStockQty() + saveItemModel.getStoreQty());
            adminService.addItemQty(item);
    }


    @GetMapping("/storesList")
    public String storesList(Model model){
        List<StoresListModel> storesList = adminService.storesFindAll();
        model.addAttribute("storesList", storesList);
        return "/admin/storesList";
    }

    @GetMapping("/stores/{storeCode}")
    public String storesDetail(@PathVariable("storeCode") Long storeCode, Model model){
        try {
            StoresListModel storeItem = adminService.storesFindByCode(storeCode).orElseThrow(NoSuchElementException::new);
            model.addAttribute("store", storeItem);
        } catch (NoSuchElementException e) {
            log.info("조회 실패");
            return "/admin/storesList";
        }

        return "/admin/storesDetail";
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
