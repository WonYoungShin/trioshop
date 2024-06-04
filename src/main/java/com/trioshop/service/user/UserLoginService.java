package com.trioshop.service.user;

import com.trioshop.exception.UserNotFoundException;
import com.trioshop.model.dto.user.LoginModel;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.repository.dao.user.UserLoginDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserLoginDao userLoginDao;

    public UserInfoBySession isValidUser(LoginModel loginModel) {

        UserInfoBySession userInfoBySession = userLoginDao.loginUser(loginModel);
        try{
            if(userInfoBySession == null) throw new RuntimeException();
        }catch (RuntimeException e){
            throw new UserNotFoundException();
        }
        return userInfoBySession;
    }
}
