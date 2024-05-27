package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.SalesCondition;
import com.trioshop.model.dto.admin.SalesModel;
import com.trioshop.service.admin.OrderManagementService;
import com.trioshop.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trioAdmin")
public class OrderManagementController {

    private final OrderManagementService orderService;
    private final DateUtils dateUtil;

    @GetMapping("/sales")
    public String yearSales(@ModelAttribute SalesCondition salesCondition, Model model) {

        List<SalesModel> yearlySales = orderService.yearSales(salesCondition);
        double totalSales = yearlySales.stream()
                .mapToDouble(SalesModel::getTotalSales)
                .sum();

        model.addAttribute("totalSales", totalSales);
        model.addAttribute("yearlySales", yearlySales);
        return "admin/sales/yearlySales";
    }

    @GetMapping("/monthSales")
    public String monthSales(@ModelAttribute SalesCondition salesCondition,
                             Model model) {

        if (salesCondition.getYear() == null) {
            salesCondition.setYear(dateUtil.getCurrentYear());
        }

        // '전체'를 기본값으로 설정 (null 값)
        if (salesCondition.getMonth() == null) {
            salesCondition.setMonth(null);
        }

        List<SalesModel> salesList = orderService.monthSales(salesCondition);

        // 총매출 계산
        double totalSales = salesList.stream().mapToDouble(SalesModel::getTotalSales).sum();

        // 모델에 데이터 추가
        model.addAttribute("yearList", dateUtil.getYearList());
        model.addAttribute("monthList", dateUtil.getMonthList());
        model.addAttribute("salesList", salesList);
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("selectedYear", salesCondition.getYear());
        model.addAttribute("selectedMonth", salesCondition.getMonth());

        return "admin/sales/monthlySales";
    }

    @GetMapping("/orderStatus")
    public String orderStatus(Model model) {

        return "admin/orderStatusList";
    }
}