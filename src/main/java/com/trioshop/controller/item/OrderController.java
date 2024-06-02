package com.trioshop.controller.item;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.model.dto.item.OrderItemList;
import com.trioshop.model.dto.item.OrdersEntity;
import com.trioshop.model.dto.user.UserAddressInfo;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.service.item.ItemService;
import com.trioshop.service.item.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders") // 주문 상세 페이지로
    public String ordersPage(@RequestParam(value = "itemCodes", required = false) List<Long> itemCodes,
                             @RequestParam(value = "quantities", required = false) List<Long> quantities,
                             @SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                             Model model) {
        List<ItemInfoByUser> itemList = orderService.makeOrderItems(itemCodes, quantities);
        UserAddressInfo userAddressInfo =
                orderService.selectUserAddressInfo(userInfoBySession.getUserCode());
        model.addAttribute("userAddressInfo",userAddressInfo);
        model.addAttribute("itemList", itemList);
        return "user/itemInfo/orders";
    }

    @GetMapping("/orderList") // 주문 완료 목록으로
    public String orderListPage(@SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                                Model model) {

        List<OrderListModel> orderList = orderService.orderList(userInfoBySession.getUserCode());
        model.addAttribute("orderList", orderList);
        return "user/itemInfo/orderList";
    }

    @PostMapping("/placeOrder") // 주문로직
    public String orderProcess(@ModelAttribute OrdersEntity ordersEntity,
                               @ModelAttribute OrderItemList orderItemList,
                               @SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                               Model model) {

        ordersEntity.setUserCode(userInfoBySession.getUserCode());
        boolean check = orderService.orderProcess(ordersEntity, orderItemList.getOrderItemEntityList());
        if (check) {
            List<OrderListModel> orderList = orderService.orderList(ordersEntity.getUserCode());
            model.addAttribute("orderList", orderList);
            return "redirect:/orderList";
        } else {
            System.out.println("주문실패"); //테스트용
            return "redirect:/";
        }
    }
}
