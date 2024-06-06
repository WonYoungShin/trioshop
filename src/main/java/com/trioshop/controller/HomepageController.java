package com.trioshop.controller;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.service.item.ItemService;
import com.trioshop.utils.business.CategoryList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomepageController {
    private final ItemService itemService;
    private final CategoryList categoryList;

    @GetMapping("/") // 홈화면
    public String userList(Model model, HttpSession session) {
        String message = (String) session.getAttribute(SessionConst.ERROR_MESSAGE);
        List<ItemInfoByUser> itemList = itemService.searchItems(null);;
        model.addAttribute("itemList", itemList);
        //카테고리 목록 불러오기
        model.addAttribute("categoryList", categoryList.getCategoryList());

        if(message != null) {
            model.addAttribute(SessionConst.ERROR_MESSAGE, message);
            session.removeAttribute(SessionConst.ERROR_MESSAGE);
        }
        return "etc/homePage";
    }
}
