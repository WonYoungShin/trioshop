package com.trioshop.service.user;

import com.trioshop.model.dto.user.GuestUserJoinInfo;
import com.trioshop.model.dto.user.GuestUserLoginInfo;
import com.trioshop.model.dto.user.UserJoin;
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

    public void guestUserLoginProcess(GuestUserLoginInfo guestUserLoginInfo) {
        boolean booleanUserExist = userJoinDao.searchGuestUser(guestUserLoginInfo);
        if(booleanUserExist) {
            //검색해서 로그인
        } else {
            //가입하고 로그인
        }
    }

    // 비회원으로 로그인하는 메소드입니다.
    public GuestUserJoinInfo LoginGuestUser(GuestUserJoinInfo guestUserJoin) {
        return userJoinDao.LoginGuestUser(guestUserJoin);
    }

    // 비회원으로 회원가입하는 메소드입니다.
//    public boolean saveGuestUser(GuestUserJoinInfo guestUserJoin) {
//        try {
//            boolean isSuccess = userJoinDao.saveGuestUser(guestUserJoin);
//            if (isSuccess) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
