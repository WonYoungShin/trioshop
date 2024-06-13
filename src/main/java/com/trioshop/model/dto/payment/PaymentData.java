package com.trioshop.model.dto.payment;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentData {

    long userCode;
    String paymentKey;
    String orderId;
    long amount;
    String orderCode;
    String userName;
    String orderName;
    String userTel;
    String successUrl;
    String failUrl;
}


