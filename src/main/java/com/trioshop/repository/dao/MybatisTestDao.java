package com.trioshop.repository.dao;

import com.trioshop.model.dto.user.User;

import java.util.List;

public interface MybatisTestDao {
    public List<User> findAll();
}
