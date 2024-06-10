package com.trioshop.service.user;

import com.trioshop.exception.UserSaveFailedException;
import com.trioshop.model.dto.user.*;
import com.trioshop.repository.dao.user.UserJoinDao;
import com.trioshop.utils.handler.LoginSuccessHandler;
import com.trioshop.utils.service.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserJoinService {
    private final UserJoinDao userJoinDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

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
    private void saveUserJoinData(UserJoin userJoin) {
        try {
            UserJoinModel userJoinModel = UserJoinModel.builder()
                    .userId(userJoin.getUserId())
                    .userPasswd(passwordEncoder.encode(userJoin.getUserPasswd()))
                    .build();

            Long userCode = userJoinDao.saveUsers(userJoinModel);

//        long userCode = userJoinDao.selectUserCode(userJoin);
//        // UsersInfoEntity insert
            userJoinDao.saveUserInfo(new UsersInfoEntity(userCode,
                    userJoin.getUserName(),
                    userJoin.getUserAddress(),
                    userJoin.getUserTel(),
                    userJoin.getUserNickname()));
        } catch (Exception e) {
            throw new UserSaveFailedException("Failed to save user data: " + e.getMessage());
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
    public void guestUserJwtToken(HttpServletResponse response, UserInfoBySession guestUser){
        LoginSuccessHandler login = new LoginSuccessHandler(jwtTokenUtil);
        login.loginSuccess(response,guestUser);
    }

    protected void saveGuestUserInfo(GuestUserLoginInfo guestUserLoginInfo) {
        // TRIO_USERS 에 데이터 입력
        UsersEntity usersEntity = new UsersEntity(9,guestUserLoginInfo.getUserTel());
        // 게스트 유저의 코드를 9로 지정, id=userTel로 지정
        Long userCode = userJoinDao.insertUsersData(usersEntity);
        // 입력된 유저데이터 확인
//        long userCode = userJoinDao.sele(usersEntity);
        // TRIO_USERS_INFO 에 데이터 입력
        userJoinDao.insertUsersInfoData(new UsersInfoEntity(userCode, //
                                                            guestUserLoginInfo.getUserName(),
                                                            guestUserLoginInfo.getUserTel(),
                                             "게스트유저"
                                        ));
    }

}
