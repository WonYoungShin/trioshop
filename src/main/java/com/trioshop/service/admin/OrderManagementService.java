package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.SalesCondition;
import com.trioshop.model.dto.admin.SalesModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.OrderManagementDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final OrderManagementDao orderDao;


    public List<SalesModel> yearSales(SalesCondition salesCondition) {
        return orderDao.yearSales(salesCondition);
    }
    public List<SalesModel> monthSales(SalesCondition salesCondition) {
        return orderDao.monthSales(salesCondition);
    }
}
