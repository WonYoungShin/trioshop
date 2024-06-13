package com.trioshop.model.dto.item;

import lombok.*;

@Getter
@Setter // 객체 리스트로 받을시 필요
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemEntity {
    private String orderCode;
    private long itemCode;
    private long orderQty;
}

