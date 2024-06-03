package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DashboardModel {
    private final Long allOrderPrice;
    private final Integer allPurchaseCount;
}
