package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.*;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.model.dto.item.OrderStatusEntity;
import com.trioshop.repository.dao.admin.OrderManagementDao;
import com.trioshop.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final OrderManagementDao orderDao;


    public List<SalesModel> yearSales(SalesCondition salesCondition) {
        if (salesCondition.getYear() != null && salesCondition.getYear() < 100) {
            DateUtils dateUtils = new DateUtils();
            int year = salesCondition.getYear();
            int current = dateUtils.getCurrentYear() / 100;
            year += current * 100;
            salesCondition.setYear(year);
        }

        return orderDao.yearSales(salesCondition);
    }

    public List<SalesModel> monthSales(SalesCondition salesCondition) {
        return orderDao.monthSales(salesCondition);
    }

    @Transactional
    public DashboardModel dashboard() {
        Long allOrderPrice = orderDao.allOrderPrice();
        Integer allPurchaseQty = orderDao.allPurchaseQty();
        return new DashboardModel(allOrderPrice, allPurchaseQty);
    }

    public List<OrderListModel> orderList(StatusCondition statusCondition) {
        return orderDao.orderList(statusCondition);
    }

    public List<OrderStatusEntity> statusList() {
        return orderDao.statusList();
    }

    public boolean updateStatus(EditStatusModel editStatusModel) {
        try {
            orderDao.updateStatus(editStatusModel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<DeliveryEntity> deliveryEntityList() {
        return orderDao.deliveryEntityList();
    }

    @Transactional
    public boolean addWaybill(WaybillModel waybillModel, EditStatusModel editStatusModel) {
        try {
            orderDao.addWaybill(waybillModel);
            orderDao.updateStatus(editStatusModel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<WaybillSelectModel> findWaybillByCode(String orderCode) {
        return orderDao.findWaybillByCode(orderCode);
    }

}


