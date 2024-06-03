package com.trioshop.model.dto.item;

import lombok.*;

import java.util.List;


@Getter
@Setter // 객체 리스트로 받을시 필요
public class OrderItemList {
    private List<OrderItemEntity> orderItemEntityList;
}
