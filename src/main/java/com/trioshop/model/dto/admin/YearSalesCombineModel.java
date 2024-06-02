package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class YearSalesCombineModel {

    private final List<YearSalesModel> salesModelList;
    private final double totalSales;
}
