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

    public Integer passwordCheck(UserCodePwModel userCodePwModel){
        return userMapper.passwordCheck(userCodePwModel);
    }
    // 사용자 코드를 기반으로 사용자 정보를 찾아 반환하는 메소드입니다.
    public UserPatchModel findByUserCode(Long userCode) {
        return userMapper.findByUserCode(userCode);
    }

    public void saveUsers(UserJoin userJoin) {userMapper.saveUsers(userJoin);}
    // 사용자 아이디 중복 체크
    public UserJoin checkUserIdExists(String userId) {return userMapper.checkUserIdExists(userId);}
    public void saveUserInfo(UserJoin userJoin) {userMapper.saveUserInfo(userJoin);}

    public String findId(UserFindId userFindId) {
        return userMapper.findId(userFindId);
    }

    public Long findUserCodeByNameAndId(PasswordChangeCodeSelectModel psModel){
        return userMapper.findUserCodeByNameAndId(psModel);
    }
    public void updatePw(UserCodePwModel updateUserPwModel){
        userMapper.updatePw(updateUserPwModel);
    }


//    // 비밀번호 찾기를 위해 사용자 정보를 조회하고, 새로운 비밀번호로 업데이트하는 메소드입니다.
//    public Long findUserCodeByNameAndId(PasswordChangeCodeSelectModel userFindPw) {
//        try {
//
//            if (result != null) {
//                return userMapper.updatePw(userFindPw.getUserId(), userFindPw.getUserPasswd());
//            } else {
//                return false; // 일치하는 정보가 없을 경우
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false; // 에러 발생 시
//        }
//    }

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

    public void patchUserInfo(UserPatchPostModel userPatchModel) {
        userMapper.patchUserInfo(userPatchModel);
    }

    // 변경된 정보가 있는지 확인하는 메소드입니다.
    public boolean changedInfo(UserPatchModel userPatch) {
        return userPatch.getUserNickname() != null ||
                userPatch.getUserAddress() != null ||
                userPatch.getUserTel() != null;
    }


}

