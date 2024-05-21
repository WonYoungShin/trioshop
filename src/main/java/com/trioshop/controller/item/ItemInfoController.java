package com.trioshop.controller.item;

import com.trioshop.model.dto.item.ItemInfoByCart;
import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.service.item.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
                                    @RequestParam(value = "categoryCode", required = false) String categoryCode) {

        ModelAndView mv = new ModelAndView();

        List<ItemInfoByUser> itemList = itemService.searchItems(searchText, categoryCode);
        mv.addObject("itemList", itemList);
        mv.setViewName("/user/itemInfo/itemList");

        return mv;
    }

    @RequestMapping("/cart")//카트 페이지로
    public ModelAndView cartPage(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        UserInfoBySession userInfoBySession = (UserInfoBySession) session.getAttribute("UserInfoBySession");
        System.out.println(userInfoBySession.getUserCode());
        List<ItemInfoByCart> cartItems = itemService.cartItemList(userInfoBySession.getUserCode());
        for (ItemInfoByCart cartItem : cartItems) {
            System.out.println(cartItem);
        }
        mv.addObject("cartItems", cartItems);
        mv.setViewName("/user/itemInfo/cart");
        return mv;
    }

    @RequestMapping("/item/{itemCode}") // 상품 상세 페이지로
    public ModelAndView itemDetailPage(@ModelAttribute ItemInfoByUser item) {

        ModelAndView mv = new ModelAndView();
        mv.addObject("item", item);
        mv.setViewName("/user/itemInfo/itemPage");
        return mv;
    }

    @RequestMapping("/orders") // 주문 페이지로
    public ModelAndView ordersPage(@ModelAttribute List<ItemInfoByUser> orderItemList) {

        ModelAndView mv = new ModelAndView();
        mv.addObject("orderItemList", orderItemList);
        mv.setViewName("/user/itemInfo/orders");
        return mv;
    }

    @RequestMapping("/orderList") // 주문 리스트 페이지로
    public String orderListPage() {
        return "/user/itemInfo/orderList";
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

