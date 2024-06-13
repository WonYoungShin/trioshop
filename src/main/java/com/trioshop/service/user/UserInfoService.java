package com.trioshop.service.user;

import com.trioshop.exception.MatchingFailedPassword;
import com.trioshop.exception.SessionExpirationException;
import com.trioshop.exception.UserNotFoundException;
import com.trioshop.model.dto.user.*;
import com.trioshop.repository.dao.user.UserInfoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoDao userInfoDao;
    private final PasswordEncoder passwordEncoder;

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
    public void passwordCheck(Long userCode,String password){
        String findPassword = userInfoDao.passwordCheck(userCode);

        if(password == null || !passwordEncoder.matches(password,findPassword)){
            throw new MatchingFailedPassword();
        }
    }

    public PasswordChangeCodeAndStatus findUserCodeByNameAndId(PasswordChangeCodeSelectModel psModel) {
        Long result = userInfoDao.findUserCodeByNameAndId(psModel);
        if(Objects.nonNull(result)){
            return new PasswordChangeCodeAndStatus(result,true);
        }
        throw new UserNotFoundException();
    }
    public void updatePw(Long userCode, String password) {
        if(Objects.isNull(userCode)) throw new SessionExpirationException();
        password = passwordEncoder.encode(password);
        userInfoDao.updatePw(new UserCodePwModel(userCode,password));
    }
    // 사용자 정보를 업데이트하는 메소드입니다.
    public void patchUserInfo(Long userCode,UserPatchModel userPatchModel) {
        userInfoDao.patchUserInfo(new UserPatchPostModel(userCode,userPatchModel));
    }
}
