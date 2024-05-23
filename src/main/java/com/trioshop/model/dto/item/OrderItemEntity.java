package com.trioshop.model.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class OrderItemEntity
{

    private String orderCode;
    private long itemCode;
    private long order_qty;
}
//    private long orderItemCode;