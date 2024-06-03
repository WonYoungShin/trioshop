package com.trioshop.repository.dao.item;

import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.item.ItemCodeAndQty;
import com.trioshop.model.dto.item.OrderItemEntity;
import com.trioshop.model.dto.item.OrdersEntity;
import com.trioshop.model.dto.user.UserAddressInfo;
import com.trioshop.repository.mybatis.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDao {
    private final ItemMapper itemMapper;

    public List<OrderListModel> orderList(long userCode) {
        return itemMapper.orderList(userCode);
    }

    public void insertOrders(OrdersEntity ordersEntity) {
        itemMapper.insertOrders(ordersEntity);
    }

    public void insertOrderItems(OrderItemEntity orderItemEntity) {
        itemMapper.insertOrderItems(orderItemEntity);
    }

    public void updateStockQty(ItemCodeAndQty itemCodeAndQty) {
        itemMapper.updateStockQty(itemCodeAndQty);
    }

    //주문서에 고객의 기존정보를 미리 기입
    public UserAddressInfo selectUserAddressInfo (long userCode) {
        return  itemMapper.selectUserAddressInfo(userCode);
    }

    public void deleteItemsFromCart(long userCode, List<Long> itemCodeList) {
        itemMapper.deleteItemsFromCart(userCode, itemCodeList);
    }
}
