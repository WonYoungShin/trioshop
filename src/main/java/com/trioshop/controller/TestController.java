package com.trioshop.controller;

import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.repository.dao.MybatisTestDao;

import com.trioshop.repository.dao.item.ItemInfoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final MybatisTestDao myBatisTestDao;
    @Autowired
    ItemInfoDao itemInfoDao;
    @RequestMapping("/")
    public ModelAndView userList(Model model) {
        System.out.println("homePage");
        ModelAndView mv = new ModelAndView();

        List<ItemInfoByUser> itemList = itemInfoDao.findAllItem();
        mv.addObject("itemList", itemList);
        mv.setViewName("/etc/homePage");
        return mv;
    }
}

