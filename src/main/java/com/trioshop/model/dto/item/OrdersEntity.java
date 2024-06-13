package com.trioshop.model.dto.item;

import lombok.*;

@Getter
@Setter // 객체로 주소및 값들을 가져오기 위하여
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersEntity {

    private String orderCode;
    private long userCode;
    private String orderReceiver;
    private String orderDestination;
    private String orderDate;
    private String orderTel;
    private String statusCode;

}