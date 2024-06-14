package com.trioshop.controller.item;

import com.trioshop.model.dto.item.OrderItemList;
import com.trioshop.model.dto.item.OrdersEntity;
import com.trioshop.model.dto.payment.PaymentData;
import com.trioshop.service.item.OrderService;
import com.trioshop.service.payment.TossPaymentService;

import com.trioshop.utils.service.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/toss")
@RequiredArgsConstructor
public class TossPaymentController {
    private final TossPaymentService tossPaymentService;


    @RequestMapping("/confirm")
    @ResponseBody
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String requestData) throws Exception {
        return tossPaymentService.confirmPayment(requestData);
    }

    @PostMapping
    public String tossPayProcess(@ModelAttribute OrdersEntity ordersEntity,
                                 @ModelAttribute OrderItemList orderItemList,
                                 @RequestParam("totalPrice") long totalPrice,
                                 Model model) {
            PaymentData paymentData = tossPaymentService.makeTossPaymentData(ordersEntity, orderItemList.getOrderItemEntityList(), totalPrice);
            model.addAttribute("paymentData", paymentData);
            return "payment/checkout";
    }

    @GetMapping("/success")
    public String paymentSuccess(HttpServletRequest request, Model model) {
        return "payment/success";
    }

    @GetMapping("/fail")
    public String paymentFail(HttpServletRequest request, Model model) {
        String failCode = request.getParameter("code");
        String failMessage = request.getParameter("message");
        model.addAttribute("code", failCode);
        model.addAttribute("message", failMessage);
        return "payment/fail";
    }
}
