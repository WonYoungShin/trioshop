// UserInfoDao.java
package com.trioshop.repository.dao.user;

import com.trioshop.model.dto.user.*;
import com.trioshop.repository.mybatis.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserInfoDao {

    @Autowired
    private UserMapper userMapper;

    public UserInfoBySession loginUser(String userId, String userPasswd) {
        UserIdPasswd userIdPasswd = new UserIdPasswd(userId, userPasswd);
        return userMapper.loginUser(userIdPasswd);
    }


    public UserFindId findId(String userName, String userTel) {
        return userMapper.findId(userName, userTel);
    }

    public UserFindPw findPw(String userName, String userId) {
        return userMapper.findPw(userName, userId);
    }


    @Transactional
    public boolean saveUserInfo(UserJoin userJoin) {
        try {
            userMapper.saveUsers(userJoin);
            userMapper.saveUserInfo(userJoin);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean patchUser(UserPatch userPatch) {
        try {
            boolean patchUserSuccess = userMapper.patchUser(userPatch);
            boolean patchUserPwSuccess = userMapper.patchUserPw(userPatch);
            return patchUserSuccess && patchUserPwSuccess;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changedInfo(UserPatch userPatch) {
        return userPatch.getUserNickname() != null ||
                userPatch.getUserAddress() != null ||
                userPatch.getUserTel() != null;
    }

}

