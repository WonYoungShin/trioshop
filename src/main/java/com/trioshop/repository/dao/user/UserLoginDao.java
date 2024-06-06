package com.trioshop.repository.dao.user;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.model.dto.user.UserJoinModel;
import com.trioshop.repository.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserLoginDao {
    private final UserMapper userMapper;

    public UserInfoBySession loginUser(UserJoinModel loginModel) {
        return userMapper.loginUser(loginModel);
    }
    public UserInfoBySession loadUserByUsername(String userId){
        return userMapper.loadUserByUsername(userId);
    }

}
