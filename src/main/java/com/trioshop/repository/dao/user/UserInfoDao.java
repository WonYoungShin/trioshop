// UserInfoDao.java
package com.trioshop.repository.dao.user;

import com.trioshop.model.dto.user.UserJoin;
import com.trioshop.model.dto.user.UserFindId;
import com.trioshop.model.dto.user.UserFindPw;
import com.trioshop.model.dto.user.UserIdPasswd;
import com.trioshop.model.dto.user.UserInfoBySession;
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

    public UserInfoBySession findId(String userName, String userTel) {
        UserFindId userFindId = new UserFindId(userName, userTel);
        return userMapper.findId(userName, userTel);
    }

    public UserInfoBySession findPw(String userName, String userId) {
        UserFindPw userFindPw = new UserFindPw(userName, userId);
        return userMapper.findPw(userName, userId);
    }
    @Transactional
    public boolean saveUserInfo(UserJoin userJoin) {
        try {
            // TRIO_USERS 테이블에 사용자 정보 저장
            userMapper.saveUsers(userJoin);
            userMapper.saveUserInfo(userJoin);
            return true; // 저장에 성공하면 true 반환
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 저장에 실패하면 false 반환
        }
    }
}
