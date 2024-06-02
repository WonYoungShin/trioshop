package com.trioshop.repository.dao.item;

import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.item.*;
import com.trioshop.model.dto.user.UserAddressInfo;
import com.trioshop.repository.mybatis.ItemMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemInfoDao {
    private final ItemMapper itemMapper;

    public List<ItemInfoByUser> findAllItem() {
        return itemMapper.findAllItem();
    }

    public List<CategoryEntity> categoryList() {
        return itemMapper.categoryList();
    }

    public List<ItemInfoByUser> searchItems(ItemCondition itemCondition) {
        return itemMapper.searchItems(itemCondition);
    }
    public List<ItemInfoByUser> itemInfoByCodes(List<Long> itemCodes) {
        return itemMapper.itemInfoByCodes(itemCodes);
    }

    public ItemInfoByUser itemInfoByCode(long itemCode) {
        return itemMapper.itemInfoByCode(itemCode);
    }

    public List<ItemDetailSearch> itemDetailNameSearch(String itemName) {
        return itemMapper.itemDetailNameSearch(itemName);
    }
}
