package com.trioshop.repository.dao.user;

import com.trioshop.model.dto.user.LoginModel;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.repository.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserLoginDao {
    private final UserMapper userMapper;

    public UserInfoBySession loginUser(LoginModel loginModel) {
        return userMapper.loginUser(loginModel);
    }

}
