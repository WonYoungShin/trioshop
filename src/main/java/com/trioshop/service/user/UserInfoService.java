package com.trioshop.service.user;

import com.trioshop.controller.exception.MatchingFailedPassword;
import com.trioshop.controller.exception.SessionExpirationException;
import com.trioshop.controller.exception.UserNotFoundException;
import com.trioshop.model.dto.user.*;
import com.trioshop.repository.dao.user.UserInfoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoDao userInfoDao;

    // 사용자 코드를 기반으로 사용자 정보를 가져오는 메소드입니다.
    public UserPatchModel findByUserCode(Long userCode) {
        return userInfoDao.findByUserCode(userCode);
    }

    // 사용자 이름과 전화번호를 기반으로 아이디를 찾는 메소드입니다.
    public String isFindId(UserFindId userFindId) {
        String id = userInfoDao.findId(userFindId);

        if (Objects.isNull(id)) throw new UserNotFoundException();

        return id;
    }
    public void passwordCheck(Long userCode, String password){
        Integer check = userInfoDao.passwordCheck(new UserCodePwModel(userCode, password));
        if(check == 0){
            throw new MatchingFailedPassword();
        }
    }

    public PasswordChangeCodeAndStatus findUserCodeByNameAndId(PasswordChangeCodeSelectModel psModel) {
        Long result = userInfoDao.findUserCodeByNameAndId(psModel);
        if(Objects.nonNull(result)){
            return new PasswordChangeCodeAndStatus(result,true);
        }
        throw new MatchingFailedPassword();
    }
    public void updatePw(Long userCode, String password) {
        if(Objects.isNull(userCode)) throw new SessionExpirationException();
        userInfoDao.updatePw(new UserCodePwModel(userCode,password));
    }

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
    public boolean changedInfo(UserPatchModel userPatch) {
        return userInfoDao.changedInfo(userPatch);
    }

    // 사용자 정보를 업데이트하는 메소드입니다.
    public void patchUserInfo(Long userCode,UserPatchModel userPatchModel) {
        userInfoDao.patchUserInfo(new UserPatchPostModel(userCode,userPatchModel));
    }
}
