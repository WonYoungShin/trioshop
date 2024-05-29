package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.item.*;
import com.trioshop.model.dto.user.UserAddressInfo;
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

    List<OrderListModel> orderList(long userCode);

    void insertOrders(OrdersEntity ordersEntity);

    void insertOrderItems(OrderItemEntity orderItemEntity);

    void updateStockQty(ItemCodeAndQty itemCodeAndQty);

    void deleteItemsFromCart(long userCode, List<Long> itemCodeList);

    void insertCartItem(CartEntity cartEntity);

    void deleteCartItem(CartEntity cartEntity);

    int selectCartItem(CartEntity cartEntity);

    void updateCartItem(CartEntity cartEntity);

    List<String> findColors(String itemName);

    List<String> findSizes(String itemName);
    ItemDetailSearch itemDetailSearch(ItemDetailSearch itemDetailSearch);
    List<ItemDetailSearch> itemDetailNameSearch(String itemName);
    UserAddressInfo selectUserAddressInfo (long userCode);
}
