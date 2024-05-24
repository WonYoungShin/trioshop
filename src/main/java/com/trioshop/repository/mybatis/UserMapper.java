package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.user.*;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    UserInfoBySession loginUser(UserIdPasswd userIdPasswd);

    UserFindId findId(@Param("userName") String userName,
                      @Param("userTel") String userTel);

    UserFindPw findPw(@Param("userName") String userName,
                      @Param("userId") String userId);

    // TRIO_USERS 테이블에 사용자 정보 저장
    boolean saveUsers(UserJoin userJoin);
    boolean saveUserInfo(UserJoin userJoin);


    boolean patchUserPw(UserPatch userPatch);
    boolean patchUser(UserPatch userPatch);
}
