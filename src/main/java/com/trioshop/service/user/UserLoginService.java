package com.trioshop.service.user;

import com.trioshop.SessionConst;
import com.trioshop.exception.UserNotFoundException;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.model.dto.user.UserJoinModel;
import com.trioshop.repository.dao.user.UserLoginDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserDetailsService{
    private final UserLoginDao userLoginDao;

//    public UserInfoBySession isValidUser(UserJoinModel loginModel) {
//
//        UserInfoBySession userInfoBySession = userLoginDao.loginUser(loginModel);
//        try{
//            if(userInfoBySession == null) throw new RuntimeException();
//        }catch (RuntimeException e){
//            throw new UserNotFoundException();
//        }
//        return userInfoBySession;
//    }

    @Override
    public UserInfoBySession loadUserByUsername(String username) {
        UserInfoBySession userInfoBySession = userLoginDao.loadUserByUsername(username);
        if (userInfoBySession == null) {
            throw new UsernameNotFoundException(username);
        }
        return userInfoBySession;
    }



//    @Override
//    public UserInfoBySession loadUserByUsername(String username) {
//        UserInfoBySession userInfoBySession = userLoginDao.loadUserByUsername(username);
//        if(userInfoBySession == null){
//            throw new UsernameNotFoundException(username);
//        }
//        return userInfoBySession;
//    }
}
