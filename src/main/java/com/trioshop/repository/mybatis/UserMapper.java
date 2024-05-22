package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.user.UserIdPasswd;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.model.dto.user.UserJoin;
import com.trioshop.model.dto.user.UserPatch;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    UserInfoBySession loginUser(UserIdPasswd userIdPasswd);

    UserInfoBySession findId(@Param("userName") String userName,
                             @Param("userTel") String userTel);

    UserInfoBySession findPw(@Param("userName") String userName,
                             @Param("userId") String userId);

    // TRIO_USERS 테이블에 사용자 정보 저장
    boolean saveUsers(UserJoin userJoin);
    boolean saveUserInfo(UserJoin userJoin);


    boolean patchUserPw(UserPatch userPatch);
    boolean patchUser(UserPatch userPatch);
}
