package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.PurchaseItemModel;
import com.trioshop.model.dto.admin.StoreItemModel;
import com.trioshop.repository.dao.admin.AdminDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminDao adminDao;

    public AddItemModel itemSave(AddItemModel itemModel){
        return adminDao.itemSave(itemModel);
    }
    public PurchaseItemModel purchaseSave(PurchaseItemModel itemModel){
        return adminDao.purchaseSave(itemModel);
    }
    public StoreItemModel storeSave(StoreItemModel itemModel){
        return adminDao.storeSave(itemModel);
    }


}
