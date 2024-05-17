package com.trioshop.controller.item;

import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.repository.dao.item.ItemInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ItemInfoController {
    @Autowired
    ItemInfoDao itemInfoDao;
    @RequestMapping("/itemList")//상품 전체리스트 페이지로
    public ModelAndView itemListPage() {

        ModelAndView mv = new ModelAndView();
        List<ItemInfoByUser> itemList = itemInfoDao.findAllItem();
        mv.addObject("itemList", itemList);
        mv.setViewName("/user/itemInfo/itemList");
        return mv;
    }

    @RequestMapping("/cart")//카트 페이지로
    public String cartPage() {
        return "/user/itemInfo/cart";
    }

    @RequestMapping("/item/{itemCode}") // 상품 상세 페이지로
    public String itemDetailPage () {
        return "/user/itemInfo/itemPage";
    }

    @RequestMapping("/orders") // 주문 페이지로
    public String ordersPage () {
        return "/user/itemInfo/orders";
    }

    @RequestMapping("/orderList") // 주문 리스트 페이지로
    public String orderListPage () {
        return "/user/itemInfo/orderList";
    }

}
