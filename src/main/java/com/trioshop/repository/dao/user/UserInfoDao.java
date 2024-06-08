// UserInfoDao.java
package com.trioshop.repository.dao.user;

import com.trioshop.model.dto.user.*;
import com.trioshop.repository.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserInfoDao {
    private final UserMapper userMapper;

    // 로그인 기능을 제공하는 메소드입니다. 사용자 아이디와 비밀번호를 받아와서 해당 정보로 로그인을 시도하고, 로그인에 성공하면 세션에 사용자 정보를 저장하여 반환합니다.

    public String passwordCheck(Long userCode){
        return userMapper.passwordCheck(userCode);
    }
    // 사용자 코드를 기반으로 사용자 정보를 찾아 반환하는 메소드입니다.
    public UserPatchModel findByUserCode(Long userCode) {
        return userMapper.findByUserCode(userCode);
    }


    public String findId(UserFindId userFindId) {
        return userMapper.findId(userFindId);
    }

    public Long findUserCodeByNameAndId(PasswordChangeCodeSelectModel psModel){
        return userMapper.findUserCodeByNameAndId(psModel);
    }
    public void updatePw(UserCodePwModel updateUserPwModel){
        userMapper.updatePw(updateUserPwModel);
    }
    public void patchUserInfo(UserPatchPostModel userPatchModel) {
        userMapper.patchUserInfo(userPatchModel);
    }

}

