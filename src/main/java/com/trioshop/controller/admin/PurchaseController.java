package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.PurchaseItemModel;
import com.trioshop.model.dto.admin.PurchaseListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.service.admin.PurchaseService;
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
@RequestMapping("/trioAdmin/purchase")
@Slf4j
@RequiredArgsConstructor
public class PurchaseController implements AdminController<PurchaseItemModel>{
    private final PurchaseService purchaseService;
    private final CategoryList categoryList;
    @GetMapping
    @Override
    public String savePage() {
        log.info("homeController/purchase");
        return "/admin/purchase";
    }

    @PostMapping
    @Override
    public String save(@ModelAttribute PurchaseItemModel itemModel) {
        System.out.println(itemModel);
        PurchaseItemModel saveItemModel = purchaseService.save(itemModel);
        return "redirect:/trioAdmin/purchase/list";
    }

    @GetMapping("/list")
    @Override
    public String findAll(@ModelAttribute ItemCondition itemCondition, Model model) {
        List<PurchaseListModel> purchaseList = purchaseService.findAll(itemCondition);
        model.addAttribute("categoryList",categoryList.getCategoryList());
        model.addAttribute("purchaseList", purchaseList);
        return "/admin/purchaseList";
    }
    @GetMapping("/{purchaseCode}")
    @Override
    public String findByCode(@PathVariable("purchaseCode") Long code, Model model) {
        try {
            PurchaseListModel purchaseItem = purchaseService.findByCode(code).orElseThrow(NoSuchElementException::new);
            model.addAttribute("purchase", purchaseItem);
        } catch (NoSuchElementException e) {
            log.info("조회 실패");
            return "/admin/purchaseList";
        }

        return "/admin/purchaseDetail";
    }

    @DeleteMapping("/{purchaseCode}")
    @ResponseBody
    public ResponseEntity<?> deletePurchaseByCode(@PathVariable("purchaseCode") Long purchaseCode) {
        try {
            purchaseService.deleteByCode(purchaseCode);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("삭제 중 오류가 발생했습니다.");
        }
    }
}
