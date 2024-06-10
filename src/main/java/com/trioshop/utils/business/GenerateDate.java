package com.trioshop.utils.business;

import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateDate {
    HttpSession session;
    //orderCode 생성 로직
    public static String generateOrderCode(long userCode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
        return userCode + dateStr;
    }
    public static String generateOrderDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        return sdf.format(new Date());
    }
}
