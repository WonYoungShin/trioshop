package com.trioshop.service.item;

import com.trioshop.exception.ApplicationException;
import com.trioshop.exception.ExceptionType;
import com.trioshop.model.dto.item.*;
import com.trioshop.model.dto.user.UserAddressInfo;
import com.trioshop.repository.dao.item.ItemDao;
import com.trioshop.repository.dao.item.OrderDao;
import com.trioshop.utils.business.GenerateDate;
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
    private final ItemDao itemInfoDao;

    public List<OrderListByUser> orderListByUser(long userCode) {
        return orderDao.orderListByUser(userCode);
    }

    // 주문 로직
    @Transactional
    public boolean orderProcess(OrdersEntity ordersEntity,
                                List<OrderItemEntity> orderItemList) {
        try {
            // Orders 테이블 입력
            OrdersEntity ordersEntityResult = makeOrdersEntity(ordersEntity);
            // OrderItem 테이블 입력
            List<Long> deleteCartCodeList = makeOrderItemEntity(orderItemList, ordersEntityResult.getOrderCode());
            // 구매 품목 카트 에서 제외
            orderDao.deleteItemsFromCart(ordersEntityResult.getUserCode(),deleteCartCodeList);
            return true;
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            throw new ApplicationException(ExceptionType.ORDER_OUT_OF_STOCK);
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
        OrdersEntity ordersEntityResult = new OrdersEntity(orderCode,
                                                           ordersEntity.getUserCode(),
                                                           ordersEntity.getOrderReceiver(),
                                                           ordersEntity.getOrderDestination(),
                                                           GenerateDate.generateOrderDate(),
                                                           ordersEntity.getOrderTel(),
                                                           "10");
        orderDao.insertOrders(ordersEntityResult);
        return ordersEntityResult;
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
                int checkUdate = orderDao.updateStockQty(itemCodeAndQty);
                itemCodeList.add(orderItemEntity.getItemCode());
                if (checkUdate == 0) {
                    log.error("재고 부족으로 주문 실패: itemCode = " + orderItemEntity.getItemCode());
                    throw new ApplicationException(ExceptionType.ORDER_OUT_OF_STOCK);
                }
                //throw new InsufficientStockException("재고가 부족합니다: " + orderItemEntity.getItemCode(), e);
            }
        return itemCodeList;
    }
}
