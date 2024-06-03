package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.ItemQtyModel;
import com.trioshop.model.dto.admin.StoreItemModel;
import com.trioshop.model.dto.admin.StoresListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.service.admin.StoresService;
import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
@Controller
@RequestMapping("/trioAdmin/stores")
@Slf4j
@RequiredArgsConstructor
public class StoresController implements AdminController<StoreItemModel> {
    private final StoresService storesService;
    private final CategoryList categoryList;

    @GetMapping
    @Override
    public String savePage(){
        log.info("homeController/stores");
        return "/admin/stores";
    }
    @Override
    @PostMapping
    public String save(@ModelAttribute StoreItemModel itemModel) {
        log.info(itemModel.toString());
        StoreItemModel saveItemModel = storesService.save(itemModel);
        try {
            addStockQty(saveItemModel);
        } catch (NoSuchElementException e) {
            log.info("입고 처리실패 실패");
            return "/admin/stores";
        }
        return "redirect:/trioAdmin/stores/list";
    }

    private void addStockQty(StoreItemModel itemModel) {
        ItemQtyModel item = storesService.itemFindById(itemModel.getItemCode()).orElseThrow(NoSuchElementException::new);
        item.setStockQty(item.getStockQty() + itemModel.getStoreQty());
        storesService.addItemQty(item);
    }

    @GetMapping("/list")
    @Override
    public String findAll(@ModelAttribute ItemCondition itemCondition, Model model) {
        List<StoresListModel> storesList = storesService.findAll(itemCondition);
        model.addAttribute("categoryList",categoryList.getCategoryList());
        model.addAttribute("storesList", storesList);
        return "/admin/storesList";
    }
    @GetMapping("/{storeCode}")
    @Override
    public String findByCode(@PathVariable("storeCode") Long code, Model model)  {
        try {
            StoresListModel storeItem = storesService.findByCode(code).orElseThrow(NoSuchElementException::new);
            model.addAttribute("store", storeItem);
        } catch (NoSuchElementException e) {
            log.info("조회 실패");
            return "/admin/storesList";
        }

        return "/admin/storesDetail";
    }
    @DeleteMapping("/{storeCode}")
    @ResponseBody
    public ResponseEntity<?> deleteStoreCode(@PathVariable("storeCode") Long storeCode) {
        try {
            // 재고 수량 조정 시 문제가 발생하면 예외를 발생시킵니다.
            if (delStockQty(storeCode)) {
                throw new RuntimeException("Stock quantity adjustment failed");
            }
            // 재고 수량 조정이 성공하면 저장소 항목을 삭제합니다.
            storesService.deleteByCode(storeCode);
            return ResponseEntity.ok().build();
        }  catch (Exception e) {
            // 일반 예외 처리
            return ResponseEntity.status(500).body("삭제 중 오류가 발생했습니다.");
        }
    }

    private boolean delStockQty(Long delStoreCode) {
        try {
            // StoreItem을 찾습니다.
            StoresListModel storeItem = storesService.findByCode(delStoreCode)
                    .orElseThrow(() -> new NoSuchElementException("Store item not found for code: " + delStoreCode));
            // Item을 찾습니다.
            ItemQtyModel item = storesService.itemFindById(storeItem.getItemCode())
                    .orElseThrow(() -> new NoSuchElementException("Item not found for code: " + storeItem.getItemCode()));

            // 재고 수량을 업데이트합니다.
            item.setStockQty(item.getStockQty() - storeItem.getStoresQty());
            storesService.addItemQty(item);

            return false;
        } catch (NoSuchElementException e) {
            log.info("No such element exception: " + e.getMessage());
            return true;
        } catch (Exception e) {
            log.error("Error during stock quantity adjustment", e);
            return true;
        }
    }

//    private boolean delStockQty(Long delStoreCode) {
//        try {
//            // StoreItem을 찾습니다.
//            StoresListModel storeItem = adminService.storesFindByCode(delStoreCode).orElseThrow(NoSuchElementException::new);
//            if (storeItem == null) {
//                log.info("Store item not found");
//                return true;
//            }
//
//            // Item을 찾습니다.
//            ItemQtyModel item = adminService.itemFindById(storeItem.getItemCode()).orElseThrow(NoSuchElementException::new);
//            log.info("Item found");
//
//            // 재고 수량을 업데이트합니다.
//            item.setStockQty(item.getStockQty() - storeItem.getStoresQty());
//            adminService.addItemQty(item);
//
//            return false;
//        } catch (NoSuchElementException e) {
//            log.info("No such element exception: " + e.getMessage());
//            return true;
//        } catch (Exception e) {
//            log.error("Error during stock quantity adjustment", e);
//            return true;
//        }
//    }
}
