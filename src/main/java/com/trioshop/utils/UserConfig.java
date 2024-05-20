package com.trioshop.utils;

import com.trioshop.model.dto.user.UserInfoBySession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    //기본 유저 코드 0
    @Bean
    public UserInfoBySession userInfoBySession() {
        return new UserInfoBySession(0);
    }
}

