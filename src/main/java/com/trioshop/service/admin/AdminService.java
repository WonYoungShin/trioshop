package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.*;
import com.trioshop.repository.dao.admin.AdminDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<PurchaseListModel> purchaseFindAll(){
        return adminDao.purchaseFindAll();
    }

    public List<StoresListModel> storesFindAll(){
        return adminDao.storesFindAll();
    }

    public List<StockModel> stockFindAll(){
        return adminDao.stockFindAll();
    }

    public Optional<AddItemQtyModel> itemFindById(Long itemCode){
        return adminDao.itemFindByCode(itemCode);
    }

    public void addItemQty(AddItemQtyModel item){
        adminDao.addItemQty(item);
    }


}
