package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.user.UserIdPasswd;
import com.trioshop.model.dto.user.UserInfoBySession;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserInfoBySession loginUser(UserIdPasswd userIdPasswd);
}
