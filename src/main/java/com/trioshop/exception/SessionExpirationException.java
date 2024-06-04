package com.trioshop.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "세션이 만료되었습니다.")
public class SessionExpirationException extends RuntimeException{
}
