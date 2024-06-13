package com.trioshop.service.user;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.repository.dao.user.UserLoginDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserDetailsService{
    private final UserLoginDao userLoginDao;

    @Override
    public UserInfoBySession loadUserByUsername(String username) {
        UserInfoBySession userInfoBySession = userLoginDao.loadUserByUsername(username);
        if (userInfoBySession == null) {
            throw new UsernameNotFoundException(username);
        }
        return userInfoBySession;
    }


//시큐리티 적용 전
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
}
