package com.trioshop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateDate {
    //orderCode 생성 로직
    public static String generateOrderCode(long userCode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String dateStr = sdf.format(new Date());
        return userCode + "-" + dateStr;
    }
    public static String generateOrderDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        return sdf.format(new Date());
    }
}
