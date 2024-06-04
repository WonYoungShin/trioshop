package com.trioshop.repository.dao.user;

import com.trioshop.model.dto.user.*;
import com.trioshop.repository.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserJoinDao {
    private final UserMapper userMapper;
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
    public void insertUsersData(UsersEntity usersEntity) {
        userMapper.insertUsersData(usersEntity);
    }
    public void insertUsersInfoData(UsersInfoEntity usersInfoEntity) {
        userMapper.insertUsersInfoData(usersInfoEntity);
    }
    public Long selectGuestUsersEntity(UsersEntity usersEntity) {
        return userMapper.selectGuestUsersEntity(usersEntity);
    }

//    public void saveGuestUser (GuestUserJoinInfo guestUserJoinInfo) {
//        return userMapper.saveGuestUser(guestUserJoinInfo);
//    }



}
