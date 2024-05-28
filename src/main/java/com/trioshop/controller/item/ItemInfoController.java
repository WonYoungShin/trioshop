package com.trioshop.controller.item;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.item.*;
import com.trioshop.model.dto.user.UserAddressInfo;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.service.item.ItemService;
import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemInfoController {
    private final ItemService itemService;
    //카테고리 목록 싱글톤으로 관리
    private final CategoryList categoryList;

    @GetMapping("/") // 홈화면
    public String userList(Model model) {
        List<ItemInfoByUser> itemList = itemService.findAllItem();
        model.addAttribute("itemList", itemList);
        //카테고리 목록 불러오기
        model.addAttribute("categoryList", categoryList.getCategoryList());
        return "etc/homePage";
    }
    @GetMapping("/itemList") // 전체아이템 목록화면
    public String itemListPage(Model model) {
        List<ItemInfoByUser> itemList = itemService.findAllItem();
        model.addAttribute("itemList", itemList);
        model.addAttribute("categoryList", categoryList.getCategoryList());
        return "user/itemInfo/itemList";
    }

    @GetMapping("/searchItems") // 상품 검색 페이지로
    public String searchItems(@ModelAttribute ItemCondition itemCondition,
                              Model model) {
        List<ItemInfoByUser> itemList = itemService.searchItems(itemCondition);
        model.addAttribute("itemList", itemList);
        model.addAttribute("categoryList", categoryList.getCategoryList());
        return "user/itemInfo/itemList";
    }

    @GetMapping("/cart") // 카트 페이지로
    public String cartPage(@SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                           Model model) {

        List<ItemInfoByCart> cartItems = itemService.cartItemList(userInfoBySession.getUserCode());
        model.addAttribute("cartItems", cartItems);
        return "user/itemInfo/cart";
    }

    @PostMapping("/addCart") //
    public String addCartItem(@RequestParam("itemCode") long itemCode,
                              @RequestParam("cartItemQty") long cartItemQty,
                              @SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                              Model model) {
        itemService.addCartItem(new CartEntity(userInfoBySession.getUserCode(), itemCode, cartItemQty));
        //return "user/itemInfo/itemList";
        return "redirect:itemList";
    }
    @PostMapping("/addCarts") //
    public String addCartItems(@RequestParam(value = "itemCodes", required = false) List<Long> itemCodes,
                               @RequestParam(value = "quantities", required = false) List<Long> quantities,
                              @SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                              Model model) {
        itemService.addCartItems(userInfoBySession.getUserCode(), itemCodes, quantities);

        return "redirect:itemList";
    }
    @PostMapping("/cart/remove")
    public String deleteCartItem (@RequestParam("itemCode") long itemCode,
                                  @SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                                  Model model ){
        //userCode 와 itemCode 만으로 이루어진 생성자 호출
        itemService.deleteCartItem(new CartEntity(userInfoBySession.getUserCode(), itemCode));
        return "redirect:/cart";
    }

    @GetMapping("/item/{itemCode}")
    public String itemDetailPage(@PathVariable("itemCode") long itemCode,
                                 Model model) {
        ItemInfoByUser item = itemService.itemInfoByCode(itemCode);

        List<ItemDetailSearch> itemLists = itemService.itemDetailNameSearch(item.getItemName());
        model.addAttribute("itemLists", itemLists);

        model.addAttribute("item", item);
        return "user/itemInfo/itemPage";
    }

    @PostMapping("/orders") // 주문 상세 페이지로
    public String ordersPage(@RequestParam(value = "itemCodes", required = false) List<Long> itemCodes,
                             @RequestParam(value = "quantities", required = false) List<Long> quantities,
                             @SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                             Model model) {
        List<ItemInfoByUser> itemList = itemService.makeOrderItems(itemCodes, quantities);
        UserAddressInfo userAddressInfo =
                itemService.selectUserAddressInfo(userInfoBySession.getUserCode());
        model.addAttribute("userAddressInfo",userAddressInfo);
        model.addAttribute("itemList", itemList);
        return "user/itemInfo/orders";
    }

    @GetMapping("/orderList") // 주문 완료 목록으로
    public String orderListPage(@SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                                Model model) {

        List<ItemInfoByOrderList> orderList = itemService.orderList(userInfoBySession.getUserCode());
        model.addAttribute("orderList", orderList);
        return "user/itemInfo/orderList";
    }

    @PostMapping("/placeOrder") // 주문로직
    public String orderProcess(@ModelAttribute OrdersEntity ordersEntity,
                               @ModelAttribute OrderItemList orderItemList,
                               @SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                               Model model) {

        ordersEntity.setUserCode(userInfoBySession.getUserCode());
        boolean check = itemService.orderProcess(ordersEntity, orderItemList.getOrderItemEntityList());
        if (check) {
            List<ItemInfoByOrderList> orderList = itemService.orderList(ordersEntity.getUserCode());
            model.addAttribute("orderList", orderList);
            return "redirect:/orderList";
        } else {
            System.out.println("주문실패"); //테스트용
            return "redirect:/";
        }
    }
}

