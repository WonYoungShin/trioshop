package com.trioshop.dao;

import com.trioshop.dto.User;
import com.trioshop.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MybatisTestDaoImpl implements MybatisTestDao {

    private final UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
