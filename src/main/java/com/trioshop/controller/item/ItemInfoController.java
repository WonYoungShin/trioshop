package com.trioshop.controller.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemInfoController {
    @RequestMapping("/itemList")
    public String itemList() {
        return "/user/itemInfo/itemList";
    }

    @RequestMapping("/cart")
    public String cartPage() {
        return "/user/itemInfo/cart";
    }

    @RequestMapping("/item/{itemCode}")
    public String itemDetailPage () {
        return "/user/itemInfo/itemPage";
    }

}
