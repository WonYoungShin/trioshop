package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.PurchaseItemModel;
import com.trioshop.model.dto.admin.StoreItemModel;
import com.trioshop.repository.mybatis.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminDao {
    private final AdminMapper adminMapper;

    public AddItemModel itemSave(AddItemModel itemModel){
        adminMapper.itemSave(itemModel);
        return itemModel;
    }
    public PurchaseItemModel purchaseSave(PurchaseItemModel itemModel){
        adminMapper.purchaseSave(itemModel);
        return itemModel;
    }

    public StoreItemModel storeSave(StoreItemModel itemModel){
        adminMapper.storeSave(itemModel);
        return itemModel;
    }
}
