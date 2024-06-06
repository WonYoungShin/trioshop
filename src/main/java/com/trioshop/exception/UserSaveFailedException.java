package com.trioshop.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "사용자 데이터를 저장하는 중에 오류가 발생했습니다.")
public class UserSaveFailedException extends RuntimeException {
    public UserSaveFailedException(String message) {
        super(message);
    }
}