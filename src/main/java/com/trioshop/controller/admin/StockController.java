package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.StockModel;
import com.trioshop.model.dto.admin.UpdateItemModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.service.admin.StockService;
import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/trioAdmin/stock")
@Slf4j
@RequiredArgsConstructor
public class StockController implements AdminController<AddItemModel> {
    private final StockService stockService;
    private final CategoryList categoryList;

    @GetMapping
    @Override
    public String savePage(){
        return "/admin/addItem";
    }

    @PostMapping
    @Override
    public String save(@ModelAttribute AddItemModel itemModel) {
        stockService.save(itemModel);
        return "redirect:/trioAdmin/stock/list";
    }

    @GetMapping("/list")
    @Override
    public String findAll(ItemCondition itemCondition, Model model) {
        List<StockModel> stockList = stockService.findAll(itemCondition);
        model.addAttribute("categoryList",categoryList.getCategoryList());
        model.addAttribute("stockList", stockList);
        return "/admin/stock";
    }

    @Override
    @GetMapping("/{itemCode}")
    public String findByCode(@PathVariable("itemCode") Long code, Model model) {
        try {
            log.info("itemCode = {}",code);
            StockModel stockItem = stockService.findByCode(code).orElseThrow(NoSuchElementException::new);
            model.addAttribute("item", stockItem);
        } catch (NoSuchElementException e) {
            log.info("조회 실패");
            return "/admin/stock";
        }

        return "/admin/itemDetail";
    }

    @GetMapping("/{itemCode}/edit")
    public String itemEditForm(@PathVariable("itemCode") Long itemCode, Model model){
        try {
            log.info("itemCode = {}",itemCode);
            StockModel stockItem = stockService.findByCode(itemCode).orElseThrow(NoSuchElementException::new);
            model.addAttribute("item", stockItem);
        } catch (NoSuchElementException e) {
            log.info("조회 실패");
            return "/admin/itemDetail";
        }

        return "/admin/itemEditForm";
    }

    @PostMapping("/{itemCode}/edit")
    public String itemEditSummit(@PathVariable("itemCode")Long itemCode,@ModelAttribute StockModel item){
        UpdateItemModel updateItem = new UpdateItemModel(itemCode, item.getItemName() , item.getFactoryCode(),item.getItemColor());
        stockService.updateItem(updateItem);

        return "redirect:/trioAdmin/stock/"+itemCode;
    }
}
