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

    // 로그인 기능을 제공하는 메소드입니다. 사용자 아이디와 비밀번호를 받아와서 해당 정보로 로그인을 시도하고, 로그인에 성공하면 세션에 사용자 정보를 저장하여 반환합니다.
    public UserInfoBySession loginUser(String userId, String userPasswd) {
        UserIdPasswd userIdPasswd = new UserIdPasswd(userId, userPasswd);
        return userMapper.loginUser(userIdPasswd);
    }

    // 사용자 코드를 기반으로 사용자 정보를 찾아 반환하는 메소드입니다.
    public UserPatch findUserByUserCode(String userCode) {
        return userMapper.findUserByUserCode(userCode);
    }

    // 사용자 이름과 전화번호를 기반으로 아이디를 찾아 반환하는 메소드입니다.
    public UserFindId findId(String userName, String userTel) {
        return userMapper.findId(userName, userTel);
    }

    // 비밀번호 찾기를 위해 사용자 정보를 조회하고, 새로운 비밀번호로 업데이트하는 메소드입니다.
    @Transactional
    public boolean findAndUpdatePw(UserFindPw userFindPw) {
        try {
            UserFindPw result = userMapper.findPw(userFindPw.getUserName(), userFindPw.getUserId());
            if (result != null) {
                return userMapper.updatePw(userFindPw.getUserId(), userFindPw.getUserPasswd());
            } else {
                return false; // 일치하는 정보가 없을 경우
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 에러 발생 시
        }
    }

    // 회원가입 정보를 저장하는 메소드입니다.
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

    // 비회원으로 로그인하는 메소드입니다.
    public GuestUserJoin LoginGuestUser(GuestUserJoin guestUserJoin) {
        return userMapper.LoginGuestUser(guestUserJoin);
    }

    // 비회원으로 회원가입하는 메소드입니다.
    @Transactional
    public boolean saveGuestUser(GuestUserJoin guestUserJoin, GuestUserJoin2 guestUserJoin2) {
        try {
            userMapper.saveGuestUsers(guestUserJoin);
            userMapper.saveGuestUsers2(guestUserJoin2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 사용자 정보를 수정하는 메소드입니다. 비밀번호와 기타 정보를 모두 수정할 수 있습니다.
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

    // 변경된 정보가 있는지 확인하는 메소드입니다.
    public boolean changedInfo(UserPatch userPatch) {
        return userPatch.getUserNickname() != null ||
                userPatch.getUserAddress() != null ||
                userPatch.getUserTel() != null;
    }


}

