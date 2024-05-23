package com.trioshop.model.dto.item;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemWrapper {
    private List<OrderItemEntity> orderItemEntityList = new ArrayList<>();
}

