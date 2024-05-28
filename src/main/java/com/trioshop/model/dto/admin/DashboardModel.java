package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class DashboardModel {
    Long allOrderPrice;
    Integer allPurchaseCount;

    public DashboardModel(Long allOrderPrice, Integer allPurchaseCount) {
        this.allOrderPrice = allOrderPrice;
        this.allPurchaseCount = allPurchaseCount;
    }
}
