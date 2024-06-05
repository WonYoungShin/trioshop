package com.trioshop.controller.item;

import com.trioshop.service.payment.TossPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/toss")
public class TossPaymentController {
    TossPaymentService tossPaymentService;

    @PostMapping("/confirm")
    @ResponseBody
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody) throws Exception {
        return tossPaymentService.confirmPayment(jsonBody);
    }

    @GetMapping("/success")
    public String paymentSuccess(HttpServletRequest request, Model model) {
        return "success";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        return "payment/checkout";
    }

    @GetMapping("/fail")
    public String paymentFail(HttpServletRequest request, Model model) {
        String failCode = request.getParameter("code");
        String failMessage = request.getParameter("message");

        model.addAttribute("code", failCode);
        model.addAttribute("message", failMessage);

        return "fail";
    }
}
