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

    public UserPatch getUserByUserCode(String userCode) {
        return userInfoDao.findUserByUserCode(userCode);
    }

    public UserFindId isfindId(String userName, String userTel) {
        return userInfoDao.findId(userName, userTel);
    }

    public boolean findAndUpdatePw(UserFindPw userFindPw) {
        return userInfoDao.findAndUpdatePw(userFindPw);
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

    public boolean LoginGuestUser(GuestUserJoin guestUserJoin) {
        return userInfoDao.LoginGuestUser(guestUserJoin);
    }

    public boolean registerGuestUser(GuestUserJoin guestUserJoin) {
        return userInfoDao.saveGuestUser(guestUserJoin);
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
