package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.admin.*;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.model.dto.item.OrderStatusEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;


@Mapper
public interface OrderMapper {
    List<SalesModel> yearSales(SalesCondition salesCondition);
    List<SalesModel> monthSales(SalesCondition salesCondition);
    Long allOrderPrice();
    Integer allPurchaseQty();
    List<OrderListModel> orderList(StatusCondition statusCondition);
    List<OrderStatusEntity> statusList();

    void updateStatus(EditStatusModel editStatusModel);

    List<DeliveryEntity> deliveryEntityList();

    void addWaybill(WaybillModel waybillModel);

    Optional<WaybillSelectModel> findWaybillByCode(String orderCode);
}
