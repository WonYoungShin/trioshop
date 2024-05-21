package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFindId {
    private String userName;
    private String userTel;

    public UserFindId(String userName, String userTel) {
        this.userName = userName;
        this.userTel = userTel;
    }
}
