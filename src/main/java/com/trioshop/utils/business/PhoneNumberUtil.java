package com.trioshop.utils.business;

public class PhoneNumberUtil {

    public static String removeHyphens(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }
        return phoneNumber.replace("-", "");
    }
}