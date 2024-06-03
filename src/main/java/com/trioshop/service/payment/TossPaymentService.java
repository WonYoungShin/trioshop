package com.trioshop.service.payment;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class TossPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(TossPaymentService.class);
    private static final String WIDGET_SECRET_KEY = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";
    private static final String PAYMENT_CONFIRM_URL = "https://api.tosspayments.com/v1/payments/confirm";

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
            throw new RuntimeException("Invalid JSON format", e);
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

    private String generateAuthorizationHeader(String secretKey) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((secretKey + ":").getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedBytes, StandardCharsets.UTF_8);
    }
}

