package com.trioshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trioAdmin")
public class AdminController {

    @GetMapping
    public String index() {
        return "/admin/adminMain";
    }

    @GetMapping("/addItem")
    public String add(){
        return "/admin/addItem";
    }

    @PostMapping("/addItem")
    public String addItem(){
        return "/admin/addItem";
    }


    @GetMapping("/purchase")
    public String purchase(){
        return "/admin/purchase";
    }
    @GetMapping("/stores")
    public String stores(){
        return "/admin/stores";
    }
    @GetMapping("/chart")
    public String chart(){
        return "/admin/chart";
    }

    @GetMapping("/stock")
    public String stock(){
        return "/admin/stock";
    }

}
