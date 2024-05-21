package com.trioshop.service.item;

import com.trioshop.model.dto.item.ItemInfoByCart;
import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.repository.dao.item.ItemInfoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemInfoDao itemInfoDao;


    public List<String> categoryList() {
        return itemInfoDao.categoryList();
    }
    public List<ItemInfoByUser> searchItems(String searchText, String categoryCode) {
        return itemInfoDao.searchItems(searchText, categoryCode);
    }
    public List<ItemInfoByUser> findAllItem() {
        return itemInfoDao.findAllItem();
    }
    public ItemInfoByUser itemInfoByCode(long itemCode) {
        return itemInfoDao.itemInfoByCode(itemCode);
    }

    public List<ItemInfoByCart> cartItemList(long userCode) {
        return  itemInfoDao.cartItemList(userCode);
    }
}
