package com.trioshop.repository.dao.item;

import com.trioshop.model.dto.item.ItemInfoByUser;
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
    public List<String> categoryList() {
        return itemMapper.categoryList();
    }

    public List<ItemInfoByUser> searchItems(String searchText, String categoryCode) {
        return  itemMapper.searchItems(searchText, categoryCode);
    }
}
