package com.trioshop.controller.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "수량 조정 실패")
public class QuantityAdjustFailed extends RuntimeException{
}
