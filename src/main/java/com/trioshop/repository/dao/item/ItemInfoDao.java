package com.trioshop.repository.dao.item;

import com.trioshop.model.dto.item.*;
import com.trioshop.model.dto.user.UserAddressInfo;
import com.trioshop.repository.mybatis.ItemMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemInfoDao {
    private final ItemMapper itemMapper;

    public List<ItemInfoByUser> findAllItem() {
        return itemMapper.findAllItem();
    }

    public List<CategoryEntity> categoryList() {
        return itemMapper.categoryList();
    }

    public List<ItemInfoByUser> searchItems(ItemCondition itemCondition) {
        return itemMapper.searchItems(itemCondition);
    }

    public List<ItemInfoByUser> itemInfoByCodes(List<Long> itemCodes) {
        return itemMapper.itemInfoByCodes(itemCodes);
    }

    public ItemInfoByUser itemInfoByCode(long itemCode) {
        return itemMapper.itemInfoByCode(itemCode);
    }

    public List<ItemInfoByCart> cartItemList(long userCode) {
        return itemMapper.cartItemList(userCode);
    }

    public List<ItemInfoByOrderList> orderList(long userCode) {
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

    public void deleteItemsFromCart(long userCode, List<Long> itemCodeList) {
        itemMapper.deleteItemsFromCart(userCode, itemCodeList);
    }

    public void insertCartItem(CartEntity cartEntity) {
        itemMapper.insertCartItem(cartEntity);
    }

    public void deleteCartItem(CartEntity cartEntity) {
        itemMapper.deleteCartItem(cartEntity);
    }

    public int selectCartItem(CartEntity cartEntity) {
        return itemMapper.selectCartItem(cartEntity);
    }

    public void updateCartItem(CartEntity cartEntity) {
        itemMapper.updateCartItem(cartEntity);
    }

    public List<String> findColors(String itemName) {
        return itemMapper.findColors(itemName);
    }

    public List<String> findSizes(String itemName) {
        return itemMapper.findSizes(itemName);
    }
    public ItemDetailSearch itemDetailSearch(ItemDetailSearch itemDetailSearch) {
        return itemMapper.itemDetailSearch(itemDetailSearch);
    }
    public List<ItemDetailSearch> itemDetailNameSearch(String itemName) {
        return itemMapper.itemDetailNameSearch(itemName);
    }
    public UserAddressInfo selectUserAddressInfo (long userCode) {
        return  itemMapper.selectUserAddressInfo(userCode);
    }
}
