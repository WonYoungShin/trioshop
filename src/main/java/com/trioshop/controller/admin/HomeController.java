package com.trioshop.controller.admin;

import com.github.pagehelper.PageInfo;
import com.trioshop.model.dto.admin.DashboardModel;
import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.admin.StatusCondition;
import com.trioshop.model.dto.board.BoardContentList;
import com.trioshop.model.dto.item.OrderStatusEntity;
import com.trioshop.service.admin.OrderManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/trioAdmin")
@Controller
public class HomeController {
    private final OrderManagementService orderService;

    @GetMapping(value = {"/", ""})
    public String index(@ModelAttribute StatusCondition statusCondition,
                        @RequestParam(defaultValue = "1") int page, Model model) {

        PageInfo<OrderListModel> orderListModelPageInfo = orderService.orderList(statusCondition, page);
        List<OrderStatusEntity> statusList = orderService.statusList();
        DashboardModel dashboardModel = orderService.dashboard();

        model.addAttribute("dashboard", dashboardModel);
        model.addAttribute("statusList", statusList);
        model.addAttribute("orderList", orderListModelPageInfo.getList());
        model.addAttribute("totalPages", orderListModelPageInfo.getPages());
        return "admin/adminMain";
    }



}
