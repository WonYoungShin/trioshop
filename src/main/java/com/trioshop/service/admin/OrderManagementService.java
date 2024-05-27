package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.DashboardModel;
import com.trioshop.model.dto.admin.SalesCondition;
import com.trioshop.model.dto.admin.SalesModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.OrderManagementDao;
import com.trioshop.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final OrderManagementDao orderDao;


    public List<SalesModel> yearSales(SalesCondition salesCondition) {
        if(salesCondition.getYear() != null && salesCondition.getYear() < 100) {
            DateUtils dateUtils = new DateUtils();
            int year = salesCondition.getYear();
            int current = dateUtils.getCurrentYear() / 100;
            year += current*100;
            salesCondition.setYear(year);
        }

        return orderDao.yearSales(salesCondition);
    }
    public List<SalesModel> monthSales(SalesCondition salesCondition) {
        return orderDao.monthSales(salesCondition);
    }
    @Transactional
    public DashboardModel dashboard(){
         Long allOrderPrice  = orderDao.allOrderPrice();
         Integer allPurchaseQty = orderDao.allPurchaseQty();
         return new DashboardModel(allOrderPrice,allPurchaseQty);
    }
}
