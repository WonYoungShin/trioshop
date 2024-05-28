package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SalesModel {
    private int orderYear;
    private int orderMonth;
    private double totalSales;
}
