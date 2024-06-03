package com.trioshop.controller.item;

import com.trioshop.SessionConst;
import com.trioshop.model.dto.item.CartEntity;
import com.trioshop.model.dto.item.ItemInfoByCart;
import com.trioshop.model.dto.user.UserInfoBySession;
import com.trioshop.service.item.CartService;
import com.trioshop.service.item.ItemService;
import com.trioshop.utils.CategoryList;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart") // 카트 페이지로
    public String cartPage(@SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                           Model model) {

        List<ItemInfoByCart> cartItems = cartService.cartItemList(userInfoBySession.getUserCode());
        model.addAttribute("cartItems", cartItems);
        return "user/itemInfo/cart";
    }

    @PostMapping("/addCart") // 단일 품목 카트 추가
    public String addCartItem(@RequestParam("itemCode") long itemCode,
                              @RequestParam("cartItemQty") long cartItemQty,
                              @SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                              Model model) {
        cartService.addCartItem(new CartEntity(userInfoBySession.getUserCode(), itemCode, cartItemQty));
        //return "user/itemInfo/itemList";
        return "redirect:itemList";
    }
    @PostMapping("/addCarts") // 다중 품목 카트 추가
    public String addCartItems(@RequestParam(value = "itemCodes", required = false) List<Long> itemCodes,
                               @RequestParam(value = "quantities", required = false) List<Long> quantities,
                               @SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                               Model model) {
        cartService.addCartItems(userInfoBySession.getUserCode(), itemCodes, quantities);
        return "redirect:itemList";
    }
    @PostMapping("/cart/remove") // 카트 품목 제거
    public String deleteCartItem (@RequestParam("itemCode") long itemCode,
                                  @SessionAttribute(SessionConst.LOGIN_MEMBER) UserInfoBySession userInfoBySession,
                                  Model model ){
        //userCode 와 itemCode 만으로 이루어진 생성자 호출
        cartService.deleteCartItem(new CartEntity(userInfoBySession.getUserCode(), itemCode));
        return "redirect:/cart";
    }
}
