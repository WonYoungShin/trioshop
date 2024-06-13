package com.trioshop.controller.admin;

import com.github.pagehelper.PageInfo;
import com.trioshop.model.dto.admin.PurchaseItemModel;
import com.trioshop.model.dto.admin.PurchaseListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.service.admin.PurchaseService;
import com.trioshop.utils.business.CategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trioAdmin/purchase")
@RequiredArgsConstructor
public class PurchaseController{
    private final PurchaseService purchaseService;
    private final CategoryList categoryList;

    @GetMapping
    public String savePage() {
        return "admin/purchase";
    }

    @PostMapping
    public String save(@ModelAttribute PurchaseItemModel itemModel) {
        purchaseService.save(itemModel);
        return "redirect:/trioAdmin/purchase/list";
    }

    @GetMapping("/list")
    public String findAll(@ModelAttribute ItemCondition itemCondition,
                          @RequestParam(defaultValue = "1")int page,
                          Model model) {
        PageInfo<PurchaseListModel> purchaseListPageInfo = purchaseService.findAll(itemCondition, page);
        model.addAttribute("categoryList",categoryList.getCategoryList());
        model.addAttribute("purchaseList", purchaseListPageInfo.getList());
        model.addAttribute("totalPages", purchaseListPageInfo.getPages());
        return "admin/purchaseList";
    }

    @GetMapping("/{purchaseCode}")
    public String findByCode(@PathVariable("purchaseCode") Long code, Model model) {
        PurchaseListModel purchaseItem = purchaseService.findByCode(code);
        model.addAttribute("purchase", purchaseItem);

        return "admin/purchaseDetail";
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
