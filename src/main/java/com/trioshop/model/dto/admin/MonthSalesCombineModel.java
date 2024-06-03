package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class MonthSalesCombineModel {

    private final List<Integer> yearList;
    private final List<Integer> monthList;
    private final List<MonthSalesModel> salesList;
    private final double totalSales;
    private final SalesCondition salesCondition;
}
