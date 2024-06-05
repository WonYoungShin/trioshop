package com.trioshop.model.dto.payment;

import org.springframework.web.bind.annotation.RequestParam;

public class PaymentData {
    String paymentKey;
    String orderId;
    String amount;
}
