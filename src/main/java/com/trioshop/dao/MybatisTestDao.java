package com.trioshop.dao;

import com.trioshop.dto.User;

import java.util.List;

public interface MybatisTestDao {
    public List<User> findAll();
}
