package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.user.*;

public interface UserMapper {
    /* TRIO_USERS에서 user_id, user_passwd 정보 확인 /login */
    UserInfoBySession loginUser(LoginModel userIdPasswd);

    UserPatchModel findByUserCode(Long userCode);

    void saveUsers(UserJoin userJoin);
    UserJoin checkUserIdExists(String userId);
    void saveUserInfo(UserJoin userJoin);

    /* @Param를 사용해서 userName,userTel을 입력했을때 userId 반환하기 때문에 명시를 위해 사용했습니다. 그리고 void로 하면 null값이 반환이 되게때문에 객체명으로 사용했습니다.  /findId */
    String findId(UserFindId userFindId);

    Long findUserCodeByNameAndId(PasswordChangeCodeSelectModel psModel);

    void updatePw(UserCodePwModel updateUserPwModel);

    GuestUserJoin LoginGuestUser(GuestUserJoin guestUserJoin);
    boolean saveGuestUsers(GuestUserJoin guestUserJoin);
    boolean saveGuestUsers2(GuestUserJoin2 guestUserJoin2);

    boolean patchUserPw(UserPatchModel userPatch);
    void patchUserInfo(UserPatchPostModel userPatchModel);


    Integer passwordCheck(UserCodePwModel userCodePwModel);
}
