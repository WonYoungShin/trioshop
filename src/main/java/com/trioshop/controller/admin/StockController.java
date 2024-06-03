package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.StockModel;
import com.trioshop.model.dto.admin.UpdateItemModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.service.admin.StockService;
import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trioAdmin/stock")
@RequiredArgsConstructor
public class StockController{
    private final StockService stockService;
    private final CategoryList categoryList;

    @GetMapping
    public String savePage(){
        return "/admin/addItem";
    }

    @PostMapping
    public String save(@ModelAttribute AddItemModel itemModel) {
        stockService.save(itemModel);

        return "redirect:/trioAdmin/stock/list";
    }

    @GetMapping("/list")
    public String findAll(ItemCondition itemCondition, Model model) {
        List<StockModel> stockList = stockService.findAll(itemCondition);
        model.addAttribute("categoryList",categoryList.getCategoryList());
        model.addAttribute("stockList", stockList);
        return "/admin/stock";
    }

    @GetMapping("/{itemCode}")
    public String findByCode(@PathVariable("itemCode") Long code, Model model) {
        StockModel stockItem = stockService.findByCode(code);
        model.addAttribute("item", stockItem);

        return "/admin/itemDetail";
    }

    @GetMapping("/{itemCode}/edit")
    public String itemEditForm(@PathVariable("itemCode") Long itemCode, Model model) {

        StockModel stockItem = stockService.findByCode(itemCode);
        model.addAttribute("item", stockItem);


        return "/admin/itemEditForm";
    }

    @PostMapping("/{itemCode}/edit")
    public String itemEditSummit(@PathVariable("itemCode")Long itemCode,@ModelAttribute StockModel item){
        UpdateItemModel updateItem = new UpdateItemModel(itemCode, item.getItemName() , item.getFactoryCode(),item.getItemColor());
        stockService.updateItem(updateItem);

        return "redirect:/trioAdmin/stock/"+itemCode;
    }
}
