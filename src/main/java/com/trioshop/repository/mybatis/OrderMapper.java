package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.admin.SalesCondition;
import com.trioshop.model.dto.admin.SalesModel;
import com.trioshop.model.dto.item.ItemCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface OrderMapper {
    List<SalesModel> yearSales(SalesCondition salesCondition);
    List<SalesModel> monthSales(SalesCondition salesCondition);
    Long allOrderPrice();
    Integer allPurchaseQty();
}