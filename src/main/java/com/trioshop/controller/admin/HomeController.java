package com.trioshop.controller.admin;


import com.trioshop.utils.CategoryList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@RequestMapping("/trioAdmin")
@Slf4j
@Controller
public class HomeController {
    private final CategoryList categoryList;

    @GetMapping(value = {"/", ""})
    public String index() {
        log.info("homeController");
        return "/admin/adminMain";
    }

}