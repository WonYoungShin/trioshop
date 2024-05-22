package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.*;
import com.trioshop.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/purchase/{purchaseCode}")
    @ResponseBody
    public ResponseEntity<?> deletePurchaseByCode(@PathVariable("purchaseCode") String purchaseCode) {
        try {
            adminService.deletePurchaseByCode(purchaseCode);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("삭제 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/stores")
    public String stores(){
        return "/admin/stores";
    }

    @PostMapping("/stores")
    public String addStores(@ModelAttribute StoreItemModel itemModel, RedirectAttributes redirectAttributes){
        log.info(itemModel.toString());
        StoreItemModel saveItemModel = adminService.storeSave(itemModel);
        try {
            addStockQty(saveItemModel);
        } catch (NoSuchElementException e) {
            log.info("입고 처리실패 실패");
            return "/admin/stores";
        }
        return "redirect:/trioAdmin/storesList";
    }

    private void addStockQty(StoreItemModel itemModel) {
            ItemQtyModel item = adminService.itemFindById(itemModel.getItemCode()).orElseThrow(NoSuchElementException::new);
            item.setStockQty(item.getStockQty() + itemModel.getStoreQty());
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



    @DeleteMapping("/stores/{storeCode}")
    @ResponseBody
    public ResponseEntity<?> deleteStoreCode(@PathVariable("storeCode") Long storeCode) {
        try {
            // 재고 수량 조정 시 문제가 발생하면 예외를 발생시킵니다.
            if (delStockQty(storeCode)) {
                throw new RuntimeException("Stock quantity adjustment failed");
            }
            // 재고 수량 조정이 성공하면 저장소 항목을 삭제합니다.
            adminService.deleteStoresByCode(storeCode);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            // 특정 예외에 대해 다른 응답을 보낼 수 있습니다.
            return ResponseEntity.status(404).body("해당 항목을 찾을 수 없습니다.");
        } catch (Exception e) {
            // 일반 예외 처리
            return ResponseEntity.status(500).body("삭제 중 오류가 발생했습니다.");
        }
    }

    private boolean delStockQty(Long delStoreCode) {
        try {
            // StoreItem을 찾습니다.
            StoresListModel storeItem = adminService.storesFindByCode(delStoreCode).orElseThrow(NoSuchElementException::new);
            if (storeItem == null) {
                log.info("Store item not found");
                return true;
            }

            // Item을 찾습니다.
            ItemQtyModel item = adminService.itemFindById(storeItem.getItemCode()).orElseThrow(NoSuchElementException::new);
            log.info("Item found");

            // 재고 수량을 업데이트합니다.
            item.setStockQty(item.getStockQty() - storeItem.getStoresQty());
            adminService.addItemQty(item);

            return false;
        } catch (NoSuchElementException e) {
            log.info("No such element exception: " + e.getMessage());
            return true;
        } catch (Exception e) {
            log.error("Error during stock quantity adjustment", e);
            return true;
        }
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
