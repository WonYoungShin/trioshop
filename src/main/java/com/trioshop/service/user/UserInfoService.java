package com.trioshop.service.user;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.repository.dao.user.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfoBySession isValidUser(String userId, String userPasswd) {
        return userInfoDao.loginUser(userId, userPasswd);
    }

    public UserInfoBySession isfindId(String userName, String userTel) {
        return userInfoDao.findId(userName, userTel);
    }

    public UserInfoBySession isfindPw(String userName, String userId) {
        return userInfoDao.findPw(userName, userId);
    }
}
