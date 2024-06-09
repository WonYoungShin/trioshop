package com.trioshop.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
// 전역 예외 클래스
public class ApplicationException extends RuntimeException {
    private final ExceptionType exceptionType;
}
