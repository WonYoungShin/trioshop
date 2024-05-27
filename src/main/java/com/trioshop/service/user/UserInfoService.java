package com.trioshop.service.user;

import com.trioshop.model.dto.user.*;
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

    public UserFindId isfindId(String userName, String userTel) {
        return userInfoDao.findId(userName, userTel);
    }

    public UserFindPw isfindPw(String userName, String userId) {
        return userInfoDao.findPw(userName, userId);
    }


    public boolean registerUser(UserJoin userJoin) {
        try {
            UserInfoBySession existingUser = userInfoDao.loginUser(userJoin.getUserId(), userJoin.getUserPasswd());
            if (existingUser != null) {
                return false;
            }

            return userInfoDao.saveUserInfo(userJoin);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean changedInfo(UserPatch userPatch) {
        return userInfoDao.changedInfo(userPatch);
    }

    public boolean patchUser(UserPatch userPatch) {
        try {
            return userInfoDao.patchUser(userPatch);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
