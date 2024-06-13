package com.trioshop.service.payment;

import com.trioshop.exception.ApplicationException;
import com.trioshop.exception.ExceptionType;
import com.trioshop.model.dto.item.OrderItemEntity;
import com.trioshop.model.dto.item.OrdersEntity;
import com.trioshop.model.dto.payment.PaymentData;
import com.trioshop.repository.dao.item.OrderDao;
import com.trioshop.service.item.OrderService;
import com.trioshop.utils.business.GenerateDate;
import com.trioshop.utils.business.PhoneNumberUtil;
import com.trioshop.utils.service.CustomTransactionService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TossPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(TossPaymentService.class);
    @Value("${toss.secret.api.key}")
    private static String WIDGET_SECRET_KEY;
    private static final String PAYMENT_CONFIRM_URL = "https://api.tosspayments.com/v1/payments/confirm";
    private final CustomTransactionService transactionService;
    private TransactionStatus status;
    private final OrderService orderService;
    private final OrderDao orderDao;

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
            logger.error("Error parsing JSON request body", e);
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
            outputStream.flush();

            int code = connection.getResponseCode();
            boolean isSuccess = code == HttpURLConnection.HTTP_OK;

            responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();
            Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

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
        status = transactionService.startTransaction();
        try {
            // Orders 테이블 입력
            OrdersEntity ordersEntityResult = orderService.makeOrdersEntity(ordersEntity);
            // OrderItem 테이블 입력
            List<Long> deleteCartCodeList = orderService.makeOrderItemEntity(orderItemList, ordersEntityResult.getOrderCode());
            // 구매 품목 카트 에서 제외
            orderDao.deleteItemsFromCart(ordersEntityResult.getUserCode(),deleteCartCodeList);
            // toss 결제를 위한 PaymentData 생성
            return makePaymentData(ordersEntityResult, orderItemList, totalPrice);

        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            throw new ApplicationException(ExceptionType.ORDER_OUT_OF_STOCK);
        }
    }

    public PaymentData makePaymentData(OrdersEntity ordersEntity, List<OrderItemEntity> orderItemList, long totalPrice) {
        return PaymentData.builder()
                .userCode(ordersEntity.getUserCode())
                .orderId(GenerateDate.generateOrderCode(ordersEntity.getUserCode()))
                .amount(totalPrice)
                .userName(ordersEntity.getOrderReceiver())
                .orderName("의류"+"+ 외"+(orderItemList.size()-1))
                .userTel(PhoneNumberUtil.removeHyphens(ordersEntity.getOrderTel()))
                .successUrl("http://localhost:8080/toss/success")
                .failUrl("http://localhost:8080/toss/fail")
                .build();
    }

    private String generateAuthorizationHeader(String secretKey) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((secretKey + ":").getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedBytes, StandardCharsets.UTF_8);
    }
    @Transactional
    public void successPayment() {
        System.out.println("성공1");
        transactionService.commitTransaction(status);
    }
}

