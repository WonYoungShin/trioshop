package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.*;
import com.trioshop.repository.mybatis.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

//    public Optional<PurchaseListModel> purchaseFindCode(){
//
//    }
    public List<PurchaseListModel> purchaseFindAll(){
        return adminMapper.purchaseFindAll();
    }

    public List<StoresListModel> storesFindAll(){
        return adminMapper.storesFindAll();
    }
}
