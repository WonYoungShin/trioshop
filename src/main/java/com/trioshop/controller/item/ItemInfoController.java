package com.trioshop.controller.item;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.item.*;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.service.item.ItemService;
import com.trioshop.utils.CategoryList;
import com.trioshop.utils.StringToLongConverter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemInfoController {
    private final ItemService itemService;
    //카테고리 목록 싱글톤으로 관리
    private final CategoryList categoryList;
    @Autowired
    HttpSession session;

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

    @RequestMapping("/cart")//카트 페이지로
    public ModelAndView cartPage() {
        ModelAndView mv = new ModelAndView();
        UserInfoBySession userInfoBySession =
                (UserInfoBySession)session.getAttribute(SessionConst.LOGIN_MEMBER);
        List<ItemInfoByCart> cartItems = itemService.cartItemList(userInfoBySession.getGradeCode());
        mv.addObject("cartItems", cartItems);
        mv.setViewName("/user/itemInfo/cart");
        return mv;
    }
    @PostMapping("/addCart")
    @ResponseBody
    public String addCartItem(@RequestParam("itemCode") String itemCode) {
        System.out.println("확인1");
        try {
            return "success";
        } catch (Exception e) {
            return "failure";
        }
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
    public ModelAndView ordersPage(@RequestParam(value = "itemCodes", required = false) List<String> itemCodes
                                  ,@RequestParam(value = "quantities", required = false) List<String> quantities) {

        List<ItemInfoByUser> itemList = itemService.makeOrderItems(
                StringToLongConverter.convertListOfStringsToLongs(itemCodes),
                StringToLongConverter.convertListOfStringsToLongs(quantities));
        ModelAndView mv = new ModelAndView();
        mv.addObject("itemList", itemList);
        mv.setViewName("/user/itemInfo/orders");


//        if(itemCode != null) { // 바로 주문으로 가는경우
//            ItemInfoByUser item = itemService.itemInfoByCode(itemCode);
//            List<ItemInfoByUser> itemList = new ArrayList<>();
//            itemList.add(item);
//            mv.addObject("itemList", itemList);
//            mv.setViewName("/user/itemInfo/orders");
//        } else { // 장바구니에서의 주문
//            List<ItemInfoByCart> cartItems = itemService.cartItemList(userCode);
//            mv.addObject("itemList", cartItems);
//            mv.setViewName("/user/itemInfo/orders");
//        }
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

