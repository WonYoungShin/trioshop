package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.*;
import com.trioshop.model.dto.item.OrderStatusEntity;
import com.trioshop.repository.mybatis.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderManagementDao {
    private final OrderMapper orderMapper;
    public List<SalesModel> yearSales(SalesCondition salesCondition) {
        return orderMapper.yearSales(salesCondition);
    }
    public List<SalesModel> monthSales(SalesCondition salesCondition) {
        return orderMapper.monthSales(salesCondition);
    }

    public Long allOrderPrice(){
        return orderMapper.allOrderPrice();
    }

    public Integer allPurchaseQty(){
        return orderMapper.allPurchaseQty();
    }

    public List<OrderListModel> orderList(StatusCondition statusCondition){
        return orderMapper.orderList(statusCondition);
    }

    public List<OrderStatusEntity> statusList(){ return  orderMapper.statusList(); }

    public void updateStatus(EditStatusModel editStatusModel){
        orderMapper.updateStatus(editStatusModel);
    }

    public List<DeliveryEntity> deliveryEntityList(){
        return orderMapper.deliveryEntityList();
    }

    public void addWaybill(WaybillModel waybillModel) {
        orderMapper.addWaybill(waybillModel);
    }

    public Optional<WaybillSelectModel> findWaybillByCode(String orderCode) {
        return orderMapper.findWaybillByCode(orderCode);
    }
}
