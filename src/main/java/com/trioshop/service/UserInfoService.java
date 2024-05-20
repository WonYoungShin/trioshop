package com.trioshop.service;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.repository.dao.user.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfoBySession isValidUser(String userId, String userPasswd) {
        UserInfoBySession user = userInfoDao.loginUser(userId, userPasswd);
        return user;
    }
}
