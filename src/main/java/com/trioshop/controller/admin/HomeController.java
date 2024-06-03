package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.DashboardModel;
import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.admin.StatusCondition;
import com.trioshop.model.dto.item.OrderStatusEntity;
import com.trioshop.service.admin.OrderManagementService;
import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/trioAdmin")
@Slf4j
@Controller
public class HomeController {
    private final OrderManagementService orderService;

    @GetMapping(value = {"/", ""})
    public String index(@RequestParam(defaultValue = "") String userCode,
                        @RequestParam(defaultValue = "") String statusCode,
                        Model model) {
        StatusCondition statusCondition = new StatusCondition(userCode, statusCode);

        List<OrderListModel> orderList = orderService.orderList(statusCondition);
        List<OrderStatusEntity> statusList = orderService.statusList();
        DashboardModel dashboardModel = orderService.dashboard();

        model.addAttribute("dashboard", dashboardModel);
        model.addAttribute("statusList", statusList);
        model.addAttribute("orderList", orderList);
        return "/admin/adminMain";
    }



}
