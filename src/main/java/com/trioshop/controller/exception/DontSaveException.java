package com.trioshop.controller.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "저장 실패")
public class DontSaveException extends RuntimeException{
}
