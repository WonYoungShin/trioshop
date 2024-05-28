package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.admin.EditStatusModel;
import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.admin.SalesCondition;
import com.trioshop.model.dto.admin.SalesModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.model.dto.item.OrderStatusEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface OrderMapper {
    List<SalesModel> yearSales(SalesCondition salesCondition);
    List<SalesModel> monthSales(SalesCondition salesCondition);
    Long allOrderPrice();
    Integer allPurchaseQty();
    List<OrderListModel> orderList();
    List<OrderStatusEntity> statusList();

    void updateStatus(EditStatusModel editStatusModel);
}
