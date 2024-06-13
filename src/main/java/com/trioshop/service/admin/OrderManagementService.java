package com.trioshop.service.admin;

import com.github.pagehelper.PageInfo;
import com.trioshop.model.dto.admin.*;
import com.trioshop.model.dto.item.OrderStatusEntity;
import com.trioshop.repository.dao.admin.OrderManagementDao;
import com.trioshop.utils.business.DateUtils;
import com.trioshop.utils.service.PagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final OrderManagementDao orderDao;
    private final DateUtils dateUtil;
    private final PagingService pagingService;

    public YearSalesCombineModel yearSales(SalesCondition salesCondition) {
        if (salesCondition.getYear() != null && salesCondition.getYear() < 100) {
            DateUtils dateUtils = new DateUtils();
            int year = salesCondition.getYear();
            int current = dateUtils.getCurrentYear() / 100;
            year += current * 100;
            salesCondition = new SalesCondition(year, null);
        }

        List<YearSalesModel> yearlySales = orderDao.yearSales(salesCondition);

        double totalSales = yearlySales.stream()
                .mapToDouble(YearSalesModel::getTotalSales)
                .sum();

        return new YearSalesCombineModel(yearlySales, totalSales);
    }

    public MonthSalesCombineModel monthSales(SalesCondition salesCondition) {
        if (salesCondition.getYear() == null) {
            if(Objects.nonNull(salesCondition.getMonth())) {
                salesCondition = new SalesCondition(dateUtil.getCurrentYear(), salesCondition.getMonth());
            }else{
                salesCondition = new SalesCondition(dateUtil.getCurrentYear(),null);
            }
        }

        List<MonthSalesModel> salesList = orderDao.monthSales(salesCondition);

        double totalSales = salesList.stream().mapToDouble(MonthSalesModel::getTotalSales).sum();
        return new MonthSalesCombineModel(dateUtil.getYearList(),dateUtil.getMonthList(),salesList, totalSales, salesCondition);
    }

    @Transactional
    public DashboardModel dashboard() {
        Long allOrderPrice = orderDao.allOrderPrice();
        Integer allPurchaseQty = orderDao.allPurchaseQty();
        return new DashboardModel(allOrderPrice, allPurchaseQty);
    }

    public PageInfo<OrderListModel> orderList(StatusCondition statusCondition, int page) {
        return pagingService.getPagedData(page, () -> orderDao.orderList(statusCondition));
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

    public WaybillSelectModel findWaybillByCode(String orderCode) {
        try{
            return orderDao.findWaybillByCode(orderCode).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
    }

}


