package com.trioshop.controller;

import com.trioshop.model.dto.user.User;
import com.trioshop.repository.dao.MybatisTestDao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final MybatisTestDao myBatisTestDao;

    @RequestMapping("/")
    public String userList(Model model) {
        System.out.println("확인용1");
        List<User> userList = myBatisTestDao.findAll();
        System.out.println("확인용2");
        for (User user : userList) {
            System.out.println(user);
        }
        return "index";
    }
}

