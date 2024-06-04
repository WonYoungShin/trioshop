package com.trioshop.service.user;

import com.trioshop.model.dto.user.*;
import com.trioshop.repository.dao.user.UserJoinDao;
import org.springframework.transaction.annotation.Transactional;

public class UserJoinService {
    UserJoinDao userJoinDao;
    // saveUserInfo Dao에서 saveUsers,saveUserInfo 를 불러오고 boolean을 사용해서 판단합니다.
    // @Transactional를 사용해서 2개의 sql을 동시에 판단할 수 있게 한다.
    @Transactional
    public boolean saveUserInfo(UserJoin userJoin) {
        try {
            // 이미 해당 아이디를 가진 사용자가 존재하는지 확인하기위해서이다.
            UserJoin existingUser = userJoinDao.checkUserIdExists(userJoin.getUserId());
            if (existingUser != null) {
                return false; // 이미 존재하는 아이디이므로 회원가입 실패
            } else {
                // 아이디가 중복되지 않으므로 사용자 정보를 저장
                userJoinDao.saveUsers(userJoin);
                userJoinDao.saveUserInfo(userJoin);
                return true; // 회원가입 성공
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 예외 발생 시 회원가입 실패
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
    @Transactional
    protected void saveGuestUserInfo(GuestUserLoginInfo guestUserLoginInfo) {
        // TRIO_USERS 에 데이터 입력
        UsersEntity usersEntityResult =
                userJoinDao.insertUsersData( //
                new UsersEntity(9,guestUserLoginInfo.getUserTel()));  // 게스트 유저의 코드를 9로 지정, id=userTel로 지정
        // 입력된 유저데이터 확인
        // TRIO_USERS_INFO 에 데이터 입력
        userJoinDao.insertUsersInfoData(new UsersInfoEntity(usersEntityResult.getUserCode(), //
                                                            guestUserLoginInfo.getUserName(),
                                                            guestUserLoginInfo.getUserTel(),
                                             "게스트유저"
                                        ));
    }

}
