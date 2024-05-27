package com.trioshop.model.dto.admin;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderListModel {
    private String orderCode;
    private Long userCode;
    private String orderDate;
    private String itemName;
    private Long orderQty;
    private String statusName;

}
