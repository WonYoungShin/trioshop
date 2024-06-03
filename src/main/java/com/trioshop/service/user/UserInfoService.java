package com.trioshop.service.user;

import com.trioshop.model.dto.user.*;
import com.trioshop.repository.dao.user.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    // 사용자의 유효성을 확인하고 로그인하는 메소드입니다.
    public UserInfoBySession isValidUser(String userId, String userPasswd) {
        return userInfoDao.loginUser(userId, userPasswd);
    }

    // 사용자 코드를 기반으로 사용자 정보를 가져오는 메소드입니다.
    public UserPatch getUserByUserCode(String userCode) {
        return userInfoDao.findUserByUserCode(userCode);
    }

    // 사용자 이름과 전화번호를 기반으로 아이디를 찾는 메소드입니다.
    public UserFindId isfindId(String userName, String userTel) {
        return userInfoDao.findId(userName, userTel);
    }

    // 비밀번호를 찾아 업데이트하는 메소드입니다.
    public boolean findAndUpdatePw(UserFindPw userFindPw) {
        return userInfoDao.findAndUpdatePw(userFindPw);
    }

    // 사용자를 등록하는 메소드입니다.
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

    // 비회원으로 로그인하는 메소드입니다.
    public GuestUserJoin LoginGuestUser(GuestUserJoin guestUserJoin) {
        return userInfoDao.LoginGuestUser(guestUserJoin);
    }

    // 비회원으로 회원가입하는 메소드입니다.
    public boolean saveGuestUser(GuestUserJoin guestUserJoin, GuestUserJoin2 guestUserJoin2) {
        try {
            boolean isSuccess = userInfoDao.saveGuestUser(guestUserJoin, guestUserJoin2);
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 사용자 정보가 변경되었는지 확인하는 메소드입니다.
    public boolean changedInfo(UserPatch userPatch) {
        return userInfoDao.changedInfo(userPatch);
    }

    // 사용자 정보를 업데이트하는 메소드입니다.
    public boolean patchUser(UserPatch userPatch) {
        try {
            return userInfoDao.patchUser(userPatch);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
