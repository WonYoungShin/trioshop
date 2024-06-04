package com.trioshop.model.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MonthSalesModel {
    private final int orderMonth;
    private final double totalSales;
}
