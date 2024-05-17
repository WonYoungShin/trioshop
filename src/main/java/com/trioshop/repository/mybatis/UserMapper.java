package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();
}
