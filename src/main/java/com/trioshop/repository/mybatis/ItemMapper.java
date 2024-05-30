package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.item.*;
import com.trioshop.model.dto.user.UserAddressInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

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

    //수정필요?
    void deleteItemsFromCart(long userCode, List<Long> itemCodeList);

    void insertCartItem(CartEntity cartEntity);

    void deleteCartItem(CartEntity cartEntity);

    int selectCartItem(CartEntity cartEntity);

    void updateCartItem(CartEntity cartEntity);
    List<ItemDetailSearch> itemDetailNameSearch(String itemName);
    UserAddressInfo selectUserAddressInfo (long userCode);
}
