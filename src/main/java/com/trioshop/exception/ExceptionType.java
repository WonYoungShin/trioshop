package com.trioshop.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

//    USER_NOT_FOUND("요청하신 정보를 찾을 수 없습니다.", "/user/userInfo/notFound"),
//    LOGIN_FAILED("아이디/패스워드가 틀렸습니다.", "/user/userInfo/login"),
//    SESSION_EXPIRED("세션이 만료되었습니다.", "/user/userInfo/sessionExpired"),
//    PASSWORD_MISMATCH("패스워드가 일치하지 않습니다.", "/user/userInfo/passwordCheckForm"),
    DONT_SAVE ("저장이 실패하였습니다.", "/admin/addItem"),
    ORDER_OUT_OF_STOCK ("재고가 부족합니다.", "user/itemInfo/orders" ),

    ;// 오타 없애기 위함
    private final String message;
    private final String viewName;
}