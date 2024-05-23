package com.trioshop.controller.item;

import com.trioshop.model.dto.item.*;
import com.trioshop.service.item.ItemService;
import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemInfoController {
    private final ItemService itemService;
    //카테고리 목록 싱글톤으로 관리
    private final CategoryList categoryList;

    @GetMapping("/")
    public ModelAndView userList() {
        ModelAndView mv = new ModelAndView();
        //카테고리 목록 불러오기
        mv.addObject("categoryList", categoryList.getCategoryList());

        List<ItemInfoByUser> itemList = itemService.findAllItem();
        mv.addObject("itemList", itemList);
        mv.setViewName("/etc/homePage");
        return mv;
    }

    @RequestMapping("/itemList")//상품 전체리스트 페이지로
    public ModelAndView itemListPage() {
        ModelAndView mv = new ModelAndView();

        //카테고리 목록 불러오기
        mv.addObject("categoryList", categoryList.getCategoryList());

        List<ItemInfoByUser> itemList = itemService.findAllItem();
        mv.addObject("itemList", itemList);
        mv.setViewName("/user/itemInfo/itemList");
        return mv;
    }

    @RequestMapping("/searchItems")//상품 검색 페이지로
    public ModelAndView searchItems(@ModelAttribute ItemCondition itemCondition) {
        ModelAndView mv = new ModelAndView();
        //카테고리 목록 불러오기
        mv.addObject("categoryList", categoryList.getCategoryList());

        List<ItemInfoByUser> itemList = itemService.searchItems(itemCondition);
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
        if(itemCode != null) { // 바로 주문으로 가는경우
            ItemInfoByUser item = itemService.itemInfoByCode(itemCode);
            List<ItemInfoByUser> itemList = new ArrayList<>();
            itemList.add(item);
            mv.addObject("itemList", itemList);
            mv.setViewName("/user/itemInfo/orders");
        } else { // 장바구니에서의 주문
            List<ItemInfoByCart> cartItems = itemService.cartItemList(userCode);
            mv.addObject("itemList", cartItems);
            mv.setViewName("/user/itemInfo/orders");
        }
        return mv;
    }
    @RequestMapping("/orderList/{userCode}") // 주문 리스트 페이지로
    public ModelAndView orderListPage(@PathVariable(value = "userCode") long userCode ){
        ModelAndView mv = new ModelAndView();

        List<ItemInfoByOrderList> orderList = itemService.orderList(userCode);
        mv.addObject("orderList", orderList);
        mv.setViewName("/user/itemInfo/orderList");
        return mv;
    }
    @PostMapping("/placeOrder")
    public ModelAndView orderProcess(@ModelAttribute OrdersEntity ordersEntity,
                                     @ModelAttribute("orderItemEntityList") List<OrderItemEntity> orderItemEntityList){
        ModelAndView mv = new ModelAndView();
        System.out.println("테스트1");
        for (OrderItemEntity orderItemEntity : orderItemEntityList) {
            System.out.println("orderItemEntity = " + orderItemEntity);
        }
        boolean check = itemService.orderProcess(ordersEntity, orderItemEntityList);
        System.out.println("테스트2");
        if (check) {
            List<ItemInfoByOrderList> orderList = itemService.orderList(ordersEntity.getUserCode());
            mv.addObject("orderList", orderList);
            mv.setViewName("/user/itemInfo/orderList");
        } else {
            System.out.println("주문실패"); //테스트용
            mv.setViewName("redirect:/");
        }
        System.out.println("테스트3");
        return mv;
    }
}

