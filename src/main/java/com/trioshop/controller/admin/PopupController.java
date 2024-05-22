package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.FactoryEntity;
import com.trioshop.model.dto.popup.PopupItemModel;
import com.trioshop.service.admin.PopupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PopupController {
    private final PopupService popupService;

    @GetMapping("/popupItemList")
    public String popupItemList(Model model) {
        List<PopupItemModel> popupItemList = popupService.findByAll();
        model.addAttribute("itemList", popupItemList);
        return "admin/popupItemList";
    }
    @GetMapping("/popupFactoryList")
    public String popupFactoryList(Model model) {
        List<FactoryEntity> popupFactoryList = popupService.factoryFindByAll();
        model.addAttribute("factoryList", popupFactoryList);
        return "admin/popupFactoryList";
    }
}
