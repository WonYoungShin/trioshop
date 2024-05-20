package com.trioshop.repository.dao;

import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.repository.mybatis.AdminMapper;
import com.trioshop.repository.mybatis.ItemMapper;
import com.trioshop.repository.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MybatisTestDaoImpl implements MybatisTestDao {

    private final UserMapper userMapper;
    private final AdminMapper adminMapper;

}
