package com.trioshop.controller;

import com.trioshop.repository.dao.MybatisTestDao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final MybatisTestDao myBatisTestDao;

    @RequestMapping("/")
    public String userList(Model model) {
        System.out.println("homePage");
        return "/etc/homePage";
    }
}

