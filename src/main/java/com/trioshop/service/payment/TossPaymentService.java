package com.trioshop.service.payment;

import com.trioshop.exception.ApplicationException;
import com.trioshop.exception.ExceptionType;
import com.trioshop.model.dto.item.ItemCodeAndQty;
import com.trioshop.model.dto.item.OrderItemEntity;
import com.trioshop.model.dto.item.OrdersEntity;
import com.trioshop.model.dto.payment.PaymentData;
import com.trioshop.repository.dao.item.OrderDao;
import com.trioshop.utils.business.DateUtils;
import com.trioshop.utils.business.PhoneNumberUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TossPaymentService {

    @Value("${toss.secret.api.key}")
    private static String WIDGET_SECRET_KEY;
    private static final String PAYMENT_CONFIRM_URL = "https://api.tosspayments.com/v1/payments/confirm";

    private final OrderDao orderDao;
    private OrdersEntity ordersEntityResult;
    private List<OrderItemEntity> orderItemEntityResultList;

    public ResponseEntity<JSONObject> confirmPayment(String jsonBody) throws Exception {
        JSONParser parser = new JSONParser();
        String orderId;
        String amount;
        String paymentKey;
        try {
            JSONObject requestData = (JSONObject) parser.parse(jsonBody);
            paymentKey = (String) requestData.get("paymentKey");
            orderId = (String) requestData.get("orderId");
            amount = (String) requestData.get("amount");
        } catch (ParseException e) {
            throw new ApplicationException(ExceptionType.ORDER_FAILED_MESSAGE);
        }

        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);
        obj.put("paymentKey", paymentKey);

        String authorizations = generateAuthorizationHeader(WIDGET_SECRET_KEY);

        HttpURLConnection connection = null;
        OutputStream outputStream = null;
        InputStream responseStream = null;
        try {
            URL url = new URL(PAYMENT_CONFIRM_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", authorizations);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            outputStream = connection.getOutputStream();
            outputStream.write(obj.toString().getBytes(StandardCharsets.UTF_8));
//            outputStream.flush();

            int code = connection.getResponseCode();
            boolean isSuccess = code == HttpURLConnection.HTTP_OK;

            responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();
            Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            try {
                //orders table insert
                orderDao.insertOrders(ordersEntityResult);
                //orders item table insert
                List<Long> itemCodeList = new ArrayList<>(); //장바구니에서 구매품목을 지우기위한 List
                for (OrderItemEntity orderItemEntity : orderItemEntityResultList) {
                    orderDao.insertOrderItems(orderItemEntity);
                    ItemCodeAndQty itemCodeAndQty
                            = new ItemCodeAndQty(orderItemEntity.getItemCode(),
                            orderItemEntity.getOrderQty());
                    int checkUpdate = orderDao.updateStockQty(itemCodeAndQty);
                    itemCodeList.add(orderItemEntity.getItemCode());
                    if (checkUpdate == 0) {
                        log.error("재고 부족으로 주문 실패: itemCode = " + orderItemEntity.getItemCode());
                        throw new ApplicationException(ExceptionType.ORDER_OUT_OF_STOCK);
                    }
                }
                // 구매 품목 카트 에서 제외
                orderDao.deleteItemsFromCart(ordersEntityResult.getUserCode(),itemCodeList);
            }catch (Exception e) {
                throw new ApplicationException(ExceptionType.DONT_SAVE_TABLE);
            }

            return ResponseEntity.status(code).body(jsonObject);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (responseStream != null) {
                responseStream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    public PaymentData makeTossPaymentData(OrdersEntity ordersEntity,
                                    List<OrderItemEntity> orderItemList,
                                    long totalPrice) {
            ordersEntityResult = makeOrdersEntity(ordersEntity);
            orderItemEntityResultList = makeOrderItemEntity(orderItemList, ordersEntityResult.getOrderCode());
            // toss 결제를 위한 PaymentData 생성
            return makePaymentData(ordersEntityResult, orderItemEntityResultList, totalPrice);
    }

    public OrdersEntity makeOrdersEntity (OrdersEntity ordersEntity) {
        // userCode+현재시간 으로 orderCode생성 (userCode + "-" + dateStr)
        String orderCode = DateUtils.generateOrderCode(ordersEntity.getUserCode());
        // 주문객체생성
        ordersEntityResult = new OrdersEntity(orderCode,
                ordersEntity.getUserCode(),
                ordersEntity.getOrderReceiver(),
                ordersEntity.getOrderDestination(),
                DateUtils.generateOrderDate(),
                ordersEntity.getOrderTel(),
                "10");
        return ordersEntityResult;
    }
    public List<OrderItemEntity> makeOrderItemEntity(List<OrderItemEntity> orderItemList, String orderCode) {
        // 주문 상품 테이블 저장
        for (OrderItemEntity orderItemEntity : orderItemList) {
            orderItemEntity.setOrderCode(orderCode);
        }
        return orderItemList;
    }

    public PaymentData makePaymentData(OrdersEntity ordersEntity, List<OrderItemEntity> orderItemList, long totalPrice) {
        String firstItemName = orderDao.selectItemName(orderItemList.get(0).getItemCode());
        return PaymentData.builder()
                .userCode(ordersEntity.getUserCode())
                .orderId(ordersEntity.getOrderCode())
                .amount(totalPrice)
                .userName(ordersEntity.getOrderReceiver())
                .orderName(firstItemName+"+ 외"+(orderItemList.size()-1))
                .userTel(PhoneNumberUtil.removeHyphens(ordersEntity.getOrderTel()))
                .build();
    }

    private String generateAuthorizationHeader(String secretKey) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((secretKey + ":").getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedBytes, StandardCharsets.UTF_8);
    }
}

