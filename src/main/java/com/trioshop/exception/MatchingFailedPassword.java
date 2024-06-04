package com.trioshop.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "비밀번호가 일치하지 않습니다.")
public class MatchingFailedPassword extends RuntimeException{
}
