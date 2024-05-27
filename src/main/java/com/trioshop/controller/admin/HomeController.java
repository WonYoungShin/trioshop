package com.trioshop.controller.admin;


import com.trioshop.utils.CategoryList;
import com.trioshop.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.trioshop.utils.DateUtils.getDateUtils;


@RequiredArgsConstructor
@RequestMapping("/trioAdmin")
@Slf4j
@Controller
public class HomeController {
    private final CategoryList categoryList;

    @GetMapping(value = {"/", ""})
    public String index() {
        return "/admin/adminMain";
    }

    @GetMapping("/sales")
    public String salesList(Model model) {
        DateUtils dateUtils = getDateUtils();
//        List<LocalDate> salesDates = dateUtils.generateSalesDates(); 일자추가
        List<Integer> yearList = dateUtils.generateYearList();
        List<Integer> monthList = dateUtils.generateMonthList();

//        model.addAttribute("salesDates", salesDates);
        model.addAttribute("yearList", yearList);
        model.addAttribute("monthList", monthList);
        return "/admin/sales";
    }

}
