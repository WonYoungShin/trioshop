package com.trioshop.service.user;

import com.trioshop.model.dto.user.*;
import com.trioshop.repository.dao.user.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    // 사용자의 유효성을 확인하고 로그인하는 메소드입니다.
    public UserInfoBySession isValidUser(UserIdPasswd userIdPasswd) {
        return userInfoDao.loginUser(userIdPasswd);
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

    // saveUserInfo Dao에서 saveUsers,saveUserInfo 를 불러오고 boolean을 사용해서 판단합니다.
    // @Transactional를 사용해서 2개의 sql을 동시에 판단할 수 있게 한다.
    @Transactional
    public boolean saveUserInfo(UserJoin userJoin) {
        try {
            // 이미 해당 아이디를 가진 사용자가 존재하는지 확인하기위해서이다.
            UserJoin existingUser = userInfoDao.checkUserIdExists(userJoin.getUserId());
            if (existingUser != null) {
                return false; // 이미 존재하는 아이디이므로 회원가입 실패
            } else {
                // 아이디가 중복되지 않으므로 사용자 정보를 저장
                userInfoDao.saveUsers(userJoin);
                userInfoDao.saveUserInfo(userJoin);
                return true; // 회원가입 성공
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 예외 발생 시 회원가입 실패
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
