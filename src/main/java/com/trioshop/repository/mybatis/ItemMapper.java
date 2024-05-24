package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.item.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<ItemInfoByUser> findAllItem();
    List<ItemInfoByUser> searchItems(ItemCondition itemCondition);
    List<CategoryEntity> categoryList();
    List<ItemInfoByUser> itemInfoByCodes(List<Long> itemCodes);
    ItemInfoByUser itemInfoByCode(long itemCode);
    List<ItemInfoByCart> cartItemList(long userCode);
    List<ItemInfoByOrderList> orderList(long userCode);
    void insertOrders (OrdersEntity ordersEntity);
    void insertOrderItems (OrderItemEntity orderItemEntity);
}
