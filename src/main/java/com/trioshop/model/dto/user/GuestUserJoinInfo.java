package com.trioshop.model.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestUserJoinInfo {
    private long userCode;
    private long gradeCode;
    private String userName;
    private String userAddress;
    private String userTel;
    private String userNickname;
}
