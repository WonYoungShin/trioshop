package com.trioshop.controller.admin;

import com.trioshop.controller.exception.QuantityAdjustFailed;
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
public class StoresController {
    private final StoresService storesService;
    private final CategoryList categoryList;

    @GetMapping
    public String savePage(){
        log.info("homeController/stores");
        return "/admin/stores";
    }

    @PostMapping
    public String save(@ModelAttribute StoreItemModel itemModel) throws Exception {
        storesService.save(itemModel);

        return "redirect:/trioAdmin/stores/list";
    }

    @GetMapping("/list")
    public String findAll(@ModelAttribute ItemCondition itemCondition, Model model) {
        List<StoresListModel> storesList = storesService.findAll(itemCondition);
        model.addAttribute("categoryList",categoryList.getCategoryList());
        model.addAttribute("storesList", storesList);
        return "/admin/storesList";
    }
    @GetMapping("/{storeCode}")
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
            storesService.deleteByCode(storeCode);
            return ResponseEntity.ok().build();
        }  catch (QuantityAdjustFailed e) {
            return ResponseEntity.status(500).body("삭제 중 오류가 발생했습니다.");
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
