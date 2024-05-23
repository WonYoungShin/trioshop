package com.trioshop.model.dto.item;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemEntity {
    private String orderCode;
    private long itemCode;
    private long order_qty;
}
//    private long orderItemCode;