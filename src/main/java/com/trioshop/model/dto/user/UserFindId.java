package com.trioshop.model.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFindId {

    String userName;
    String userTel;
    String userId; // 추가된 부분

    public UserFindId(String userName, String userTel, String userId) {
        this.userName = userName;
        this.userTel = userTel;
        this.userId = userId; // 추가된 부분
    }
}
