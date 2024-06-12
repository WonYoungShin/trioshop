package com.trioshop.controller.item;

import com.trioshop.model.dto.admin.WaybillModel;
import com.trioshop.model.dto.item.OrderItemList;
import com.trioshop.model.dto.item.OrderListByUser;
import com.trioshop.model.dto.item.OrdersEntity;
import com.trioshop.model.dto.payment.PaymentData;
import com.trioshop.service.item.OrderService;
import com.trioshop.service.payment.TossPaymentService;
import com.trioshop.utils.service.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/toss")
@RequiredArgsConstructor
public class TossPaymentController {
    private final OrderService orderService;
    private final SecurityUtils securityUtils;
    private final TossPaymentService tossPaymentService;

//    @PostMapping("/confirm")
//    @ResponseBody
//    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody) throws Exception {
//        return tossPaymentService.confirmPayment(jsonBody);
//    }

    @GetMapping("/success")
    public String paymentSuccess(HttpServletRequest request, Model model) {
        System.out.println("성공");
        return "payment/success";
    }

    @GetMapping("/")
    public String tossPayProcess(@ModelAttribute OrdersEntity ordersEntity,
                                @ModelAttribute OrderItemList orderItemList,
                               Model model) { {
//        boolean check = orderService.orderProcess(ordersEntity, orderItemList.getOrderItemEntityList());
        PaymentData paymentData = new PaymentData().builder()
                .totalPrice(1)
                .userCode(securityUtils.getCurrentUserCode())
                .paymentKey("paymentkey1234")
                .orderId("orderId1234") // 영문대소문자
                .amount("의류포함 총"+"?건")
                .orderCode("ordercode1234")
                .userName("유저명1")
                .orderName("주문문명1")
                .userTel("01011111111")
                .successUrl("http://localhost:8080/toss/success")
                .failUrl("http://localhost:8080/toss/fail")
                .build();
        System.out.println("paymentData = " + paymentData);
        model.addAttribute("paymentData", paymentData);

        return "payment/checkout";
    }

//            List<OrderListByUser> orderList = orderService.orderListByUser(ordersEntity.getUserCode());
//            model.addAttribute("orderList", orderList);
//            return "redirect:/orderList";

    }
    @GetMapping("/fail")
    public String paymentFail(HttpServletRequest request, Model model) {
        System.out.println("실패");
        String failCode = request.getParameter("code");
        String failMessage = request.getParameter("message");

        model.addAttribute("code", failCode);
        model.addAttribute("message", failMessage);

        return "payment/fail";
    }
}
