package com.trioshop.service.user;

import com.trioshop.controller.exception.DontSaveException;
import com.trioshop.controller.exception.UserNotFoundException;
import com.trioshop.model.dto.user.*;
import com.trioshop.repository.dao.user.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.NoSuchObjectException;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    // 사용자의 유효성을 확인하고 로그인하는 메소드입니다.
    public UserInfoBySession isValidUser(UserIdPasswd userIdPasswd) {
        UserInfoBySession userInfoBySession = userInfoDao.loginUser(userIdPasswd);
        try{
            if(userInfoBySession == null) throw new RuntimeException();
        }catch (RuntimeException e){
            throw new UserNotFoundException();
        }
        return userInfoBySession;
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
