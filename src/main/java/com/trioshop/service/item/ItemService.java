package com.trioshop.service.item;

import com.github.pagehelper.PageInfo;
import com.trioshop.model.dto.item.*;
import com.trioshop.repository.dao.item.ItemDao;
import com.trioshop.utils.service.PagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemDao itemInfoDao;
    private final PagingService pagingService;

    public List<CategoryEntity> categoryList() {
        return itemInfoDao.categoryList();
    }

    public PageInfo<ItemInfoByUser> searchItems(ItemCondition itemCondition, int page) {
        int size=12;
        return pagingService.getPagedData(page, size, () -> itemInfoDao.searchItems(itemCondition));
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
