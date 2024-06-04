package com.trioshop.repository.dao.user;

import com.trioshop.model.dto.user.*;
import com.trioshop.repository.mybatis.UserMapper;

public class UserJoinDao {
    UserMapper userMapper;
    public void saveUsers(UserJoin userJoin) {userMapper.saveUsers(userJoin);}
    // 사용자 아이디 중복 체크
    public UserJoin checkUserIdExists(String userId) {return userMapper.checkUserIdExists(userId);}
    public void saveUserInfo(UserJoin userJoin) {userMapper.saveUserInfo(userJoin);}
    // 비회원으로 로그인하는 메소드입니다.


    // 비회원으로 회원가입하는 메소드입니다.
//    @Transactional
//    public boolean saveGuestUser(GuestUserJoinInfo guestUserJoin, GuestUserJoin2 guestUserJoin2) {
//        try {
//            userMapper.saveGuestUsers(guestUserJoin);
//            userMapper.saveGuestUsers2(guestUserJoin2);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    // 여기서부터
    public UserInfoBySession searchGuestUser (GuestUserLoginInfo guestUserLoginInfo) {
        return userMapper.searchGuestUser(guestUserLoginInfo);
    }
    public UsersEntity insertUsersData(UsersEntity usersEntity) {
        return userMapper.insertUsersData(usersEntity);
    }
    public boolean insertUsersInfoData(UsersInfoEntity usersInfoEntity) {
        return userMapper.insertUsersInfoData(usersInfoEntity);
    }

//    public void saveGuestUser (GuestUserJoinInfo guestUserJoinInfo) {
//        return userMapper.saveGuestUser(guestUserJoinInfo);
//    }



}
