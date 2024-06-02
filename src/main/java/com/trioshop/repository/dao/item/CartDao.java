package com.trioshop.repository.dao.item;

import com.trioshop.model.dto.item.CartEntity;
import com.trioshop.model.dto.item.ItemDetailSearch;
import com.trioshop.model.dto.item.ItemInfoByCart;
import com.trioshop.repository.mybatis.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartDao {
    private final ItemMapper itemMapper;

    public List<ItemInfoByCart> cartItemList(long userCode) {
        return itemMapper.cartItemList(userCode);
    }

    public void insertCartItem(CartEntity cartEntity) {
        itemMapper.insertCartItem(cartEntity);
    }

    public void deleteCartItem(CartEntity cartEntity) {
        itemMapper.deleteCartItem(cartEntity);
    }

    public int selectCartItem(CartEntity cartEntity) {
        return itemMapper.selectCartItem(cartEntity);
    }

    public void updateCartItem(CartEntity cartEntity) {
        itemMapper.updateCartItem(cartEntity);
    }


}
