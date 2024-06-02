package com.trioshop.controller.admin;

import com.trioshop.model.dto.admin.*;
import com.trioshop.model.dto.item.OrderStatusEntity;
import com.trioshop.service.admin.OrderManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/trioAdmin")
public class OrderManagementController {

    private final OrderManagementService orderService;


    @GetMapping("/sales")
    public String yearSales(@ModelAttribute SalesCondition salesCondition, Model model) {

        YearSalesCombineModel yearSalesModel = orderService.yearSales(salesCondition);

        model.addAttribute("totalSales", yearSalesModel.getTotalSales());
        model.addAttribute("yearlySales", yearSalesModel.getSalesModelList());
        return "admin/sales/yearlySales";
    }

    @GetMapping("/monthSales")
    public String monthSales(@ModelAttribute SalesCondition salesCondition,
                             Model model) {
        MonthSalesCombineModel monthSalesModel = orderService.monthSales(salesCondition);
        model.addAttribute("monthSalesModel", monthSalesModel);

        return "/admin/sales/monthlySales";
    }

    @GetMapping("/orderStatus")
    public String orderStatus(@RequestParam(defaultValue = "") String userCode, @RequestParam(defaultValue = "") String statusCode, Model model) {
        StatusCondition statusCondition = new StatusCondition(userCode, statusCode);
        List<OrderListModel> orderList = orderService.orderList(statusCondition);
        List<OrderStatusEntity> statusList = orderService.statusList();

        model.addAttribute("statusList", statusList);
        model.addAttribute("orderList", orderList);
        return "/admin/orderStatusList";
    }

    @GetMapping("/orderStatus/edit/{orderCode}")
    public String orderStatusEditForm(@PathVariable String orderCode, Model model){
        List<OrderStatusEntity> statusList = orderService.statusList();
        model.addAttribute("orderCode",orderCode);
        model.addAttribute("statusList", statusList);
        return "/admin/orderStatusEditForm";
    }

    @PostMapping("/orderStatus/edit/{orderCode}")
    @ResponseBody
    public Map<String,Object> orderStatusUpdate(@PathVariable String orderCode, @RequestParam String statusCode){
        EditStatusModel editStatusModel = new EditStatusModel(orderCode, statusCode);
        Map<String, Object> response = new HashMap<>();
        try{
            boolean s_f_checked = orderService.updateStatus(editStatusModel);
            response.put("success",s_f_checked);
        }catch (Exception e){
            response.put("success", false);
        }
        return response;
    }

    @GetMapping("/orderStatus/{orderCode}")
    public String waybillAddForm(@PathVariable String orderCode,
                                 @RequestParam(defaultValue = "", required = false) String oldDeliveryCode,
                                 @RequestParam(defaultValue = "", required = false) String oldWaybillNum,
                                 Model model) {
        List<DeliveryEntity> deliveryEntities = orderService.deliveryEntityList();

        model.addAttribute("deliveryList", deliveryEntities);
        model.addAttribute("oldDeliveryCode", oldDeliveryCode);
        model.addAttribute("oldWaybillNum", oldWaybillNum);
        model.addAttribute("orderCode", orderCode);

        return "/admin/deliveryAddForm";
    }



    @PostMapping("/orderStatus/{orderCode}")
    @ResponseBody
    public String waybillAdd(@PathVariable String orderCode, @ModelAttribute WaybillModel waybill) {

        WaybillModel waybillModel = getBuild(orderCode, waybill);
        try {
            EditStatusModel editStatusModel = new EditStatusModel(orderCode, "21");
            orderService.addWaybill(waybillModel, editStatusModel);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

    private static WaybillModel getBuild(String orderCode, WaybillModel waybill) {
        return WaybillModel.builder()
                .deliveryCode(waybill.getDeliveryCode())
                .waybillNum(waybill.getWaybillNum())
                .orderCode(orderCode)
                .build();
    }

    @GetMapping("/orderStatus/{orderCode}/information")
    public String waybillAddForm(@PathVariable String orderCode, Model model) {
        try {
            WaybillSelectModel waybillModel = orderService.findWaybillByCode(orderCode).orElseThrow(NoSuchElementException::new);
            model.addAttribute("waybillModel", waybillModel);
        }catch (NoSuchElementException e){
            log.info("운송장 정보 없음");
        }

        return "/admin/deliveryInformation";
    }

}