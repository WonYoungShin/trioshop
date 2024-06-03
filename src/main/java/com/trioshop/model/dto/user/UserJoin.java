package com.trioshop.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoin {
    private Long userCode;
    private String userId;
    private String userPasswd;
    private String userName;
    private String userAddress;
    private String userTel;
    private String userNickname;
    private Long gradeCode;

}
