package com.trioshop.model.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class YearSalesModel {
    private final int orderYear;
    private final double totalSales;
}
