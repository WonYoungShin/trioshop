package com.trioshop.controller.item;

import com.github.pagehelper.PageInfo;
import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.model.dto.item.OrderItemList;
import com.trioshop.model.dto.item.OrderListByUser;
import com.trioshop.model.dto.item.OrdersEntity;
import com.trioshop.model.dto.user.UserAddressInfo;

import com.trioshop.service.item.OrderService;
import com.trioshop.utils.service.PagingService;
import com.trioshop.utils.service.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final SecurityUtils securityUtils;

    @PostMapping("/orders") // 주문 상세 페이지로
    public String ordersPage(@RequestParam(value = "itemCodes", required = false) List<Long> itemCodes,
                             @RequestParam(value = "quantities", required = false) List<Long> quantities,
                             Model model) {
        List<ItemInfoByUser> itemList = orderService.makeOrderItems(itemCodes, quantities);
        UserAddressInfo userAddressInfo =
                orderService.selectUserAddressInfo(securityUtils.getCurrentUserCode());
        model.addAttribute("userAddressInfo",userAddressInfo);
        model.addAttribute("itemList", itemList);
        return "user/itemInfo/orders";
    }

    @GetMapping("/orderList") // 주문 완료 목록으로
    public String orderListPage(@RequestParam(defaultValue = "1") int page,
                                Model model) {
        PageInfo<OrderListByUser> orderListByUserPageInfo = orderService.orderListByUserPageInfo(securityUtils.getCurrentUserCode(),page);
        model.addAttribute("orderList", orderListByUserPageInfo.getList());
        model.addAttribute("totalPages", orderListByUserPageInfo.getPages());
        return "user/itemInfo/orderList";
    }

    @PostMapping("/placeOrder") // 주문로직
    public String orderProcess(@ModelAttribute OrdersEntity ordersEntity,
                               @ModelAttribute OrderItemList orderItemList,
                               Model model) {
        ordersEntity.setUserCode(securityUtils.getCurrentUserCode());
        boolean check = orderService.orderProcess(ordersEntity, orderItemList.getOrderItemEntityList());
        if (check) {
            List<OrderListByUser> orderList = orderService.orderListByUser(ordersEntity.getUserCode());
            model.addAttribute("orderList", orderList);
            return "redirect:/orderList";
        } else {
            System.out.println("주문실패"); //테스트용
            return "redirect:/";
        }
    }
}
