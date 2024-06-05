package com.trioshop.service.user;

import com.trioshop.model.dto.user.*;
import com.trioshop.repository.dao.user.UserJoinDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserJoinService {
    private final UserJoinDao userJoinDao;
    @Transactional
    public boolean userJoinProcess(UserJoin userJoin) {
        boolean userIdExists = userJoinDao.checkUserIdExists(userJoin.getUserId());
        if(userIdExists) {
            return true;
        } else {
            saveUserJoinData(userJoin);
            return false;
        }
    }
    public UserInfoBySession guestUserLoginProcess(GuestUserLoginInfo guestUserLoginInfo) {

        UserInfoBySession guestUser = userJoinDao.searchGuestUser(guestUserLoginInfo);
        if(guestUser != null) {
            //이미 유저가 존재하므로 바로 로그인
           return guestUser;
        } else { // 가입후 재귀하여 로그인
            saveGuestUserInfo(guestUserLoginInfo);
           return this.guestUserLoginProcess(guestUserLoginInfo);
        }
    }

    protected void saveGuestUserInfo(GuestUserLoginInfo guestUserLoginInfo) {
        // TRIO_USERS 에 데이터 입력
        UsersEntity usersEntity = new UsersEntity(9,guestUserLoginInfo.getUserTel());
        // 게스트 유저의 코드를 9로 지정, id=userTel로 지정
        userJoinDao.insertUsersData(usersEntity);
        // 입력된 유저데이터 확인
        long userCode = userJoinDao.selectUserCode(usersEntity);
        // TRIO_USERS_INFO 에 데이터 입력
        userJoinDao.insertUsersInfoData(new UsersInfoEntity(userCode, //
                                                            guestUserLoginInfo.getUserName(),
                                                            guestUserLoginInfo.getUserTel(),
                                             "게스트유저"
                                        ));
    }

    private void saveUserJoinData(UserJoin userJoin) {
        // UsersEntity insert
        userJoinDao.saveUsers(userJoin);
       long userCode = userJoinDao.selectUserCode(userJoin);
        System.out.println("userCode = " + userCode);
        // UsersInfoEntity insert
        userJoinDao.saveUserInfo(new UsersInfoEntity(userCode,
                                                     userJoin.getUserName(),
                                                     userJoin.getUserAddress(),
                                                     userJoin.getUserTel(),
                                                     userJoin.getUserNickname()));
    }
}
