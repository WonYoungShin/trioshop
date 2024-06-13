package com.trioshop.controller.item;

import com.trioshop.model.dto.admin.WaybillModel;
import com.trioshop.model.dto.item.OrderItemEntity;
import com.trioshop.model.dto.item.OrderItemList;
import com.trioshop.model.dto.item.OrderListByUser;
import com.trioshop.model.dto.item.OrdersEntity;
import com.trioshop.model.dto.payment.PaymentData;
import com.trioshop.service.item.OrderService;
import com.trioshop.service.payment.TossPaymentService;
import com.trioshop.utils.service.CustomTransactionService;
import com.trioshop.utils.service.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.transaction.Transaction;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
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
    private final HttpSession session;
    private final CustomTransactionService transactionService;

//    @PostMapping("/confirm")
//    @ResponseBody
//    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody) throws Exception {
//        return tossPaymentService.confirmPayment(jsonBody);
//    }
    @PostMapping("/")
    public String tossPayProcess(@ModelAttribute OrdersEntity ordersEntity,
                                @ModelAttribute OrderItemList orderItemList,
                               @RequestParam("totalPrice") long totalPrice,
                               Model model) { {
//        boolean check = orderService.orderProcess(ordersEntity, orderItemList.getOrderItemEntityList());
        System.out.println("totalPrice = " + totalPrice);
        System.out.println("ordersEntity = " + ordersEntity);
        System.out.println("orderItemList = " + orderItemList.getOrderItemEntityList());
        PaymentData paymentData = tossPaymentService.makeTossPaymentData(ordersEntity, orderItemList.getOrderItemEntityList(), totalPrice);

        System.out.println("paymentData = " + paymentData);
        model.addAttribute("paymentData", paymentData);
        return "payment/checkout";
    }
//            List<OrderListByUser> orderList = orderService.orderListByUser(ordersEntity.getUserCode());
//            model.addAttribute("orderList", orderList);
//            return "redirect:/orderList";
    }
    @GetMapping("/success")
    public String paymentSuccess(HttpServletRequest request, Model model) {
        tossPaymentService.successPayment();
        return "payment/success";
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
