package com.trioshop.utils;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.repository.dao.user.UserInfoDao;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserConfig {
    private final UserInfoDao userInfoDao;
    @Bean // 테스트를 위한 유저1 강제로그인
    public UserInfoBySession userInfoBySession() {
        return userInfoDao.loginUser("user1","password1");
    }
//기본 유저 코드 0
//    @Bean
//    public UserInfoBySession userInfoBySession() {
//        return new UserInfoBySession(0);
//    }
}

