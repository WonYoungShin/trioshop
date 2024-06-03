package com.trioshop.service.item;

import com.trioshop.model.dto.admin.OrderListModel;
import com.trioshop.model.dto.item.*;
import com.trioshop.model.dto.user.UserAddressInfo;
import com.trioshop.repository.dao.item.ItemInfoDao;
import com.trioshop.utils.GenerateDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemInfoDao itemInfoDao;

    public List<CategoryEntity> categoryList() {
        return itemInfoDao.categoryList();
    }

    public List<ItemInfoByUser> searchItems(ItemCondition itemCondition) {
        return itemInfoDao.searchItems(itemCondition);
    }

    public List<ItemInfoByUser> findAllItem() {
        return itemInfoDao.findAllItem();
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
