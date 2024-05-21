package com.trioshop.repository.dao.user;

import com.trioshop.model.dto.user.UserFindId;
import com.trioshop.model.dto.user.UserFindPw;
import com.trioshop.model.dto.user.UserIdPasswd;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.repository.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserInfoDao {
    private final UserMapper userMapper;

    public UserInfoBySession loginUser(String userId, String userPasswd) {
        UserIdPasswd userIdPasswd = new UserIdPasswd(userId, userPasswd);
        return userMapper.loginUser(userIdPasswd);
    }

    public UserInfoBySession findId(String userName, String userTel) {
        UserFindId userFindId = new UserFindId(userName, userTel);
        return userMapper.findId(userName,userTel);
    }

    public UserInfoBySession findPw(String userName, String userId) {
        UserFindPw userFindPw = new UserFindPw(userName, userId);
        return userMapper.findPw(userName, userId);
    }
}
