package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.user.*;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /* TRIO_USERS에서 user_id, user_passwd 정보 확인 /login */
    UserInfoBySession loginUser(UserIdPasswd userIdPasswd);

    UserPatch findUserByUserCode(String userCode);

    void saveUsers(UserJoin userJoin);
    UserJoin checkUserIdExists(String userId);
    void saveUserInfo(UserJoin userJoin);

    /* @Param를 사용해서 userName,userTel을 입력했을때 userId 반환하기 때문에 명시를 위해 사용했습니다. 그리고 void로 하면 null값이 반환이 되게때문에 객체명으로 사용했습니다.  /findId */
    UserFindId findId(@Param("userName") String userName,
                      @Param("userTel") String userTel);

    UserFindPw findPw(@Param("userName") String userName,
                      @Param("userId") String userId);

    boolean updatePw(@Param("userId") String userId, @Param("userPasswd") String userPasswd);

    GuestUserJoin LoginGuestUser(GuestUserJoin guestUserJoin);
    boolean saveGuestUsers(GuestUserJoin guestUserJoin);
    boolean saveGuestUsers2(GuestUserJoin2 guestUserJoin2);

    boolean patchUserPw(UserPatch userPatch);
    boolean patchUser(UserPatch userPatch);


}
