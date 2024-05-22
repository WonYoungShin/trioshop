package com.trioshop.controller.item;

import com.trioshop.model.dto.item.ItemInfoByCart;
import com.trioshop.model.dto.item.ItemInfoByOrderList;
import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.service.item.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.bcel.classfile.Code;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemInfoController {
    private final ItemService itemService;

    @RequestMapping("/")
    public ModelAndView userList(HttpSession session) {
        System.out.println("homePage");
        ModelAndView mv = new ModelAndView();
        categoryCheck(session);
        List<ItemInfoByUser> itemList = itemService.findAllItem();
        mv.addObject("itemList", itemList);
        mv.setViewName("/etc/homePage");
        return mv;
    }

    @RequestMapping("/itemList")//상품 전체리스트 페이지로
    public ModelAndView itemListPage(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        categoryCheck(session);
        List<ItemInfoByUser> itemList = itemService.findAllItem();
        mv.addObject("itemList", itemList);
        mv.setViewName("/user/itemInfo/itemList");
        return mv;
    }

    @RequestMapping("/searchItems")//상품 검색 페이지로
    public ModelAndView searchItems(@RequestParam(value = "searchText", required = false) String searchText,
                                    @RequestParam(value = "categoryName", required = false) String categoryName) {
        ModelAndView mv = new ModelAndView();

        List<ItemInfoByUser> itemList = itemService.searchItems(searchText, categoryName);
        mv.addObject("itemList", itemList);
        mv.setViewName("/user/itemInfo/itemList");
        return mv;
    }

    @RequestMapping("/cart/{userCode}")//카트 페이지로
    public ModelAndView cartPage(@PathVariable(value = "userCode") long userCode) {
        ModelAndView mv = new ModelAndView();

        List<ItemInfoByCart> cartItems = itemService.cartItemList(userCode);
        mv.addObject("cartItems", cartItems);
        mv.setViewName("/user/itemInfo/cart");
        return mv;
    }

    @RequestMapping("/item/{itemCode}") // 상품 상세 페이지로
    public ModelAndView itemDetailPage(@PathVariable(value = "itemCode") long itemCode) {
        ModelAndView mv = new ModelAndView();
        ItemInfoByUser item = itemService.itemInfoByCode(itemCode);
        mv.addObject("item", item);
        mv.setViewName("/user/itemInfo/itemPage");
        return mv;
    }

    @RequestMapping("/orders") // 주문 페이지로
    public ModelAndView ordersPage(@RequestParam(value = "itemCode", required = false) Long itemCode
                                  ,@RequestParam(value = "userCode", required = false) Long userCode) {
        ModelAndView mv = new ModelAndView();
        if(itemCode != null) {
            ItemInfoByUser item = itemService.itemInfoByCode(itemCode);
            List<ItemInfoByUser> itemList = new ArrayList<>();
            itemList.add(item);
            mv.addObject("itemList", itemList);
            mv.setViewName("/user/itemInfo/orders");
            return mv;
        } else {
            List<ItemInfoByCart> cartItems = itemService.cartItemList(userCode);
            mv.addObject("itemList", cartItems);
            mv.setViewName("/user/itemInfo/orders");
            return mv;
        }


    }

    @RequestMapping("/orderList") // 주문 리스트 페이지로
    public ModelAndView orderListPage(@PathVariable(value = "userCode") long userCode ){

        ModelAndView mv = new ModelAndView();
        List<ItemInfoByOrderList> orderList = itemService.orderList(userCode);
        mv.addObject("orderList", orderList);
        mv.setViewName("/user/itemInfo/orderList");
        return mv;
    }

    private void categoryCheck(HttpSession session) {
        // 세션에 categoryList가 없으면 DAO를 통해 불러오기
        List<String> categoryList = (List<String>) session.getAttribute("categoryList");
        if (categoryList == null) {
            categoryList = itemService.categoryList();
            session.setAttribute("categoryList", categoryList);
        }
    }

}

