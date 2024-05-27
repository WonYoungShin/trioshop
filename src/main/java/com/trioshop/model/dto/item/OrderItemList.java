package com.trioshop.model.dto.item;

import lombok.*;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class OrderItemList {
    private List<OrderItemEntity> orderItemEntityList;
}
