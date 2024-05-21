package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.user.UserIdPasswd;
import com.trioshop.model.dto.user.UserInfoBySession;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    UserInfoBySession loginUser(UserIdPasswd userIdPasswd);

    UserInfoBySession findId(@Param("userName") String userName,
                             @Param("userTel") String userTel);

    UserInfoBySession findPw(@Param("userName") String userName,
                             @Param("userId") String userId);
}
