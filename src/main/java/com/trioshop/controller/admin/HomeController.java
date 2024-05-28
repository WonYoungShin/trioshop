package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.DashboardModel;
import com.trioshop.service.admin.OrderManagementService;
import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/trioAdmin")
@Slf4j
@Controller
public class HomeController {
    private final OrderManagementService orderService;

    @GetMapping(value = {"/", ""})
    public String index(Model model) {
        DashboardModel dashboardModel = orderService.dashboard();
        model.addAttribute("dashboard", dashboardModel);
        return "/admin/adminMain";
    }


}
