package com.trioshop.service.item;

import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.item.ItemCodeAndQty;
import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.model.dto.item.OrderItemEntity;
import com.trioshop.model.dto.item.OrdersEntity;
import com.trioshop.model.dto.user.UserAddressInfo;
import com.trioshop.repository.dao.item.ItemInfoDao;
import com.trioshop.repository.dao.item.OrderDao;
import com.trioshop.utils.GenerateDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao orderDao;
    private final ItemInfoDao itemInfoDao;

    public List<OrderListModel> orderList(long userCode) {
        return orderDao.orderList(userCode);
    }

    // 주문 로직
    @Transactional
    public boolean orderProcess(OrdersEntity ordersEntity,
                                List<OrderItemEntity> orderItemList) {
        try {
            // Orders 테이블 입력
            OrdersEntity ordersEntityResult = makeOrdersEntity(ordersEntity);
            // OrderItem 테이블 입력
            List<Long> deleteCartCodeList = makeOrderItemEntity(orderItemList, ordersEntity.getOrderCode());
            // 구매 품목 카트 에서 제외
            orderDao.deleteItemsFromCart(ordersEntityResult.getUserCode(),deleteCartCodeList);
            return true;
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            log.info("Error processing order =  {}", e.getMessage());
            return false;
        }
    }

    public UserAddressInfo selectUserAddressInfo (long userCode) {
        return  orderDao.selectUserAddressInfo(userCode);
    }

    public List<ItemInfoByUser> makeOrderItems(List<Long> itemCodes, List<Long> quantities) {

        HashMap<Long, Long> orderMap = new HashMap<>();
        List<ItemInfoByUser> itemInfoList = itemInfoDao.itemInfoByCodes(itemCodes);

        for (int i = 0; i < itemCodes.size(); i++) {
            // itemcode 를 키로 orderQty를 값으로 하여 Map생성
            orderMap.put(itemCodes.get(i),quantities.get(i));
        }
        //Map을 이용해 구매 수량 저장
        for (ItemInfoByUser itemInfoByUser : itemInfoList) {
            itemInfoByUser.setOrderQty(orderMap.get(itemInfoByUser.getItemCode()));
        }
        return itemInfoList;
    }
    private OrdersEntity makeOrdersEntity (OrdersEntity ordersEntity) {
        // userCode+현재시간 으로 orderCode생성 (userCode + "-" + dateStr)
        String orderCode = GenerateDate.generateOrderCode(ordersEntity.getUserCode());

        // 주문 테이블 저장
        ordersEntity.setOrderCode(orderCode);
        ordersEntity.setStatusCode("10"); //DB 에서 DEFAULT '10' 입력
        ordersEntity.setOrderDate(GenerateDate.generateOrderDate()); // 시간 yyMMdd-HHmmss
        orderDao.insertOrders(ordersEntity);
        return ordersEntity;
    }
    private List<Long> makeOrderItemEntity(List<OrderItemEntity> orderItemList, String orderCode) {

        List<Long> itemCodeList = new ArrayList<>(); // 장바구니에서 구매품목을 지우기위한 List
        // 주문 상품 테이블 저장
        for (OrderItemEntity orderItemEntity : orderItemList) {
            orderItemEntity.setOrderCode(orderCode);
            orderDao.insertOrderItems(orderItemEntity);
            // 재고 업데이트 (Map으로?)
            ItemCodeAndQty itemCodeAndQty
                    = new ItemCodeAndQty(orderItemEntity.getItemCode(),
                    orderItemEntity.getOrderQty());

            // itemCode로 검색해 주문수량 update
            orderDao.updateStockQty(itemCodeAndQty);
            itemCodeList.add(orderItemEntity.getItemCode());
        }
        return itemCodeList;
    }
}
