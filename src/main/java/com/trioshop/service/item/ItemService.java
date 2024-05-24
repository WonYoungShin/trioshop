package com.trioshop.service.item;

import com.trioshop.model.dto.item.*;
import com.trioshop.repository.dao.item.ItemInfoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        List<HashMap<String, Long>> orderList = new ArrayList<>();
        List<ItemInfoByUser> itemInfoList = this.itemInfoByCodes(itemCodes);

//        if (itemCodes != null && quantities != null
//                && itemCodes.size() == quantities.size()) {
//            for (int i = 0; i < itemCodes.size(); i++) {
//                HashMap<String, Long> orderMap = new HashMap<>();
//                orderMap.put("itemCode", itemCodes.get(i));
//                orderMap.put("orderQty", quantities.get(i));
//                orderList.add(orderMap);
//
//                ItemInfoByUser itemInfoByUser = this.itemInfoByCode(itemCodes.get(i));
//                itemInfoByUser.setOrderQty(quantities.get(i));
//                itemInfoList.add(itemInfoByUser);
//            }
//        }
        // Debugging output
        for (HashMap<String, Long> order : orderList) {
            System.out.println("Order: " + order);
        }
        return itemInfoList;
    }
    @Transactional
    public boolean orderProcess(OrdersEntity ordersEntity,
                                List<OrderItemEntity> orderItemList) {
        try {
            // userCode+현재시간 으로 orderCode생성
            String orderCode = generateOrderCode(ordersEntity.getUserCode());
            // 주문 테이블 저장
            ordersEntity.setOrderCode(orderCode);
            ordersEntity.setStatusCode("10");
            ordersEntity.setOrderDate(generateOrderDate()); // 시간입력 yyMMddHHmmss
            itemInfoDao.insertOrders(ordersEntity);
            // 주문 상품 테이블 저장
            for (OrderItemEntity orderItemEntity : orderItemList) {
                orderItemEntity.setOrderCode(orderCode);
                itemInfoDao.insertOrderItems(orderItemEntity);
            }
            // 재고 업데이트

            // 기타 필요한 비즈니스 로직
            return true;
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            System.err.println("Error processing order: " + e.getMessage());
            return false;
        }
    }

    //orderCode 생성 로직
    private String generateOrderCode(long userCode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd-HHmmss");
        String dateStr = sdf.format(new Date());
        return userCode + "-" + dateStr;
    }
    private String generateOrderDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        return sdf.format(new Date());
    }
}
