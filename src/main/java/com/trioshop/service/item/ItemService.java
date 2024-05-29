package com.trioshop.service.item;

import com.trioshop.model.dto.item.*;
import com.trioshop.model.dto.user.UserAddressInfo;
import com.trioshop.repository.dao.item.ItemInfoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemInfoDao itemInfoDao;

    public List<CategoryEntity> categoryList() {
        return itemInfoDao.categoryList();
    }

    public List<ItemInfoByUser> searchItems(ItemCondition itemCondition) {
        return itemInfoDao.searchItems(itemCondition);
    }

    public List<ItemInfoByUser> findAllItem() {
        return itemInfoDao.findAllItem();
    }

    public ItemInfoByUser itemInfoByCode (long itemCode) {
        return itemInfoDao.itemInfoByCode(itemCode);
    }
    public List<ItemInfoByUser> itemInfoByCodes (List<Long> itemCodes) {
        return itemInfoDao.itemInfoByCodes(itemCodes);
    }

    public List<ItemInfoByCart> cartItemList(long userCode) {
        return itemInfoDao.cartItemList(userCode);
    }

    public List<ItemInfoByOrderList> orderList(long userCode) {
        return itemInfoDao.orderList(userCode);
    }

    public List<ItemInfoByUser> makeOrderItems(List<Long> itemCodes, List<Long> quantities) {

        HashMap<Long, Long> orderMap = new HashMap<>();
        List<ItemInfoByUser> itemInfoList = this.itemInfoByCodes(itemCodes);

            for (int i = 0; i < itemCodes.size(); i++) {
                // itemcode 를 키로 orderQty를 값으로 하여 Map생성
                orderMap.put(itemCodes.get(i),quantities.get(i));
            }

        for (ItemInfoByUser itemInfoByUser : itemInfoList) {
            itemInfoByUser.setOrderQty(orderMap.get(itemInfoByUser.getItemCode()));
        }
        return itemInfoList;
    }
    // 주문 로직
    @Transactional
    public boolean orderProcess(OrdersEntity ordersEntity,
                                List<OrderItemEntity> orderItemList) {
        try {
            // userCode+현재시간 으로 orderCode생성 (userCode + "-" + dateStr)
            String orderCode = generateOrderCode(ordersEntity.getUserCode());
            List<Long> itemCodeList = new ArrayList<>(); // 장바구니에서 구매품목을 지우기위한 List
            // 주문 테이블 저장
            ordersEntity.setOrderCode(orderCode);
            ordersEntity.setStatusCode("10");
            ordersEntity.setOrderDate(generateOrderDate()); // 시간 yyMMdd-HHmmss
            itemInfoDao.insertOrders(ordersEntity);
            // 주문 상품 테이블 저장
            for (OrderItemEntity orderItemEntity : orderItemList) {
                orderItemEntity.setOrderCode(orderCode);
                itemInfoDao.insertOrderItems(orderItemEntity);
            }
            // 재고 업데이트
            for (OrderItemEntity orderItemEntity : orderItemList) {
                ItemCodeAndQty itemCodeAndQty
                        = new ItemCodeAndQty(orderItemEntity.getItemCode(),
                                             orderItemEntity.getOrderQty());
                // itemCode로 검색해 주문수량 update
                itemInfoDao.updateStockQty(itemCodeAndQty);

                itemCodeList.add(orderItemEntity.getItemCode());
            }
            // 구매 품목 카트 에서 제외
            itemInfoDao.deleteItemsFromCart(ordersEntity.getUserCode(),itemCodeList);

            return true;
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            System.err.println("Error processing order: " + e.getMessage());
            return false;
        }
    }
    //카트 단일 항목 추가
    public void addCartItem (CartEntity cartEntity) {
        if( itemInfoDao.selectCartItem(cartEntity) != 0) { // 검색된 항목이 있다면
            itemInfoDao.updateCartItem(cartEntity); // 수량을 업데이트
        } else {
            itemInfoDao.insertCartItem(cartEntity); // cart에 insert
        }
    }
    //카트 다중 항목 추가
    public void addCartItems (long userCode, List<Long> itemCodes, List<Long> quantities) {
        for (int i = 0; i < itemCodes.size(); i++) {
            CartEntity cartEntity = new CartEntity(userCode,itemCodes.get(i),quantities.get(i));
            if( itemInfoDao.selectCartItem(cartEntity) != 0) { // 검색된 항목이 있다면
                itemInfoDao.updateCartItem(cartEntity); // 수량을 업데이트
            } else {
                itemInfoDao.insertCartItem(cartEntity); // cart에 insert
            }
        }
    }
    // 카트 항목 delete
    public void deleteCartItem (CartEntity cartEntity) {
        itemInfoDao.deleteCartItem(cartEntity);
    }

    public List<String> findColors(String itemName) {
        return itemInfoDao.findColors(itemName);
    }

    public List<String> findSizes(String itemName) {
        return itemInfoDao.findSizes(itemName);
    }

    public ItemDetailSearch itemDetailSearch(ItemDetailSearch itemDetailSearch) {
        return itemInfoDao.itemDetailSearch(itemDetailSearch);
    }
    public List<ItemDetailSearch> itemDetailNameSearch(String itemName) {
        return  itemInfoDao.itemDetailNameSearch(itemName);
    }
    public UserAddressInfo selectUserAddressInfo (long userCode) {
        return  itemInfoDao.selectUserAddressInfo(userCode);
    }
    //orderCode 생성 로직
    private String generateOrderCode(long userCode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String dateStr = sdf.format(new Date());
        return userCode + "-" + dateStr;
    }
    private String generateOrderDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        return sdf.format(new Date());
    }

}
