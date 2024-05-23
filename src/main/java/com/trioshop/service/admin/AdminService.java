package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.*;
import com.trioshop.model.dto.item.ItemCondition;
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
    public List<PurchaseListModel> purchaseFindAll(ItemCondition itemCondition){
        return adminDao.purchaseFindAll(itemCondition);
    }
    public Optional<PurchaseListModel> purchaseFindByCode(Long purchaseCode){
        return adminDao.purchaseFindByCode(purchaseCode);
    }

    public List<StoresListModel> storesFindAll(ItemCondition itemCondition){
        return adminDao.storesFindAll(itemCondition);
    }

    public Optional<StoresListModel> storesFindByCode(Long storeCode){
        return adminDao.storeFindByCode(storeCode);
    }

    public List<StockModel> stockFindAll(ItemCondition itemCondition){
        return adminDao.stockFindAll(itemCondition);
    }
    public Optional<StockModel> stockFindByCode(Long itemCode){
        return adminDao.stockFindByCode(itemCode);
    }

    public void deleteStoresByCode(Long storeCode){
        adminDao.deleteStoresByCode(storeCode);
    }

    public void updateItem(UpdateItemModel itemModel){
        adminDao.updateItem(itemModel);
    }

    public Optional<ItemQtyModel> itemFindById(Long itemCode){
        return adminDao.itemFindByCode(itemCode);
    }


    public void addItemQty(ItemQtyModel item){
        adminDao.addItemQty(item);
    }

    public void deletePurchaseByCode(String purchaseCode){
        adminDao.deletePurchaseByCode(purchaseCode);
    }

}
