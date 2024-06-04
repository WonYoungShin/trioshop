package com.trioshop.service.item;

import com.trioshop.model.dto.item.*;
import com.trioshop.repository.dao.item.ItemDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemDao itemInfoDao;

    public List<CategoryEntity> categoryList() {
        return itemInfoDao.categoryList();
    }

    public List<ItemInfoByUser> searchItems(ItemCondition itemCondition) {
        if( itemCondition != null){
            return itemInfoDao.searchItems(itemCondition);
        } else {
            return itemInfoDao.findAllItem();
        }

    }
    public ItemInfoByUser itemInfoByCode (long itemCode) {
        return itemInfoDao.itemInfoByCode(itemCode);
    }

    public List<ItemInfoByUser> itemInfoByCodes (List<Long> itemCodes) {
        return itemInfoDao.itemInfoByCodes(itemCodes);
    }
    public List<ItemDetailSearch> itemDetailNameSearch(String itemName) {
        return  itemInfoDao.itemDetailNameSearch(itemName);
    }

}
