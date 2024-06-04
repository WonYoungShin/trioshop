package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class UserFindId {
    private final String userName;
    private final String userTel;
}
