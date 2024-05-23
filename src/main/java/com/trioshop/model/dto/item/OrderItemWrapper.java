package com.trioshop.model.dto.item;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemWrapper {
    private List<OrderItemEntity> orderItemEntityList;
}

