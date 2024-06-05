package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.user.*;

public interface UserMapper {
    /* TRIO_USERS에서 user_id, user_passwd 정보 확인 /login */
    UserInfoBySession loginUser(LoginModel userIdPasswd);
    UserPatchModel findByUserCode(Long userCode);
    /* @Param를 사용해서 userName,userTel을 입력했을때 userId 반환하기 때문에 명시를 위해 사용했습니다. 그리고 void로 하면 null값이 반환이 되게때문에 객체명으로 사용했습니다.  /findId */
    String findId(UserFindId userFindId);
    Long findUserCodeByNameAndId(PasswordChangeCodeSelectModel psModel);
    void updatePw(UserCodePwModel updateUserPwModel);
    void patchUserInfo(UserPatchPostModel userPatchModel);
    Integer passwordCheck(UserCodePwModel userCodePwModel);

    //회원가입
    UserJoin checkUserIdExists(String userId);
    void saveUsers(UserJoin userJoin);
    void saveUserInfo(UsersInfoEntity usersInfoEntity);
    //게스트 유저 로그인
    UserInfoBySession searchGuestUser(GuestUserLoginInfo guestUserLoginInfo);
    void insertUsersData(UsersEntity usersEntity);
    void insertUsersInfoData(UsersInfoEntity usersInfoEntity);
    Long selectUserCode(Object object);
}
