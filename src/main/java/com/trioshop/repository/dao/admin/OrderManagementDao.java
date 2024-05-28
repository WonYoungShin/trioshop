package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.EditStatusModel;
import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.admin.SalesCondition;
import com.trioshop.model.dto.admin.SalesModel;
import com.trioshop.model.dto.item.OrderStatusEntity;
import com.trioshop.repository.mybatis.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<OrderListModel> orderList(){
        return orderMapper.orderList();
    }

    public List<OrderStatusEntity> statusList(){ return  orderMapper.statusList(); }

    public void updateStatus(EditStatusModel editStatusModel){
        orderMapper.updateStatus(editStatusModel);
    }
}
