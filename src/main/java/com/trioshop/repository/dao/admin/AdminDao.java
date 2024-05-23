package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.*;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.mybatis.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public void deletePurchaseByCode(String purchaseCode){
        adminMapper.deletePurchaseByCode(purchaseCode);
    }

    public List<PurchaseListModel> purchaseFindAll(ItemCondition itemCondition){
        return adminMapper.purchaseFindAll(itemCondition);
    }
    public Optional<PurchaseListModel> purchaseFindByCode(Long purchaseCode){
        return adminMapper.purchaseFindByCode(purchaseCode);
    }

    public List<StoresListModel> storesFindAll(ItemCondition itemCondition){

        return adminMapper.storesFindAll(itemCondition);
    }

    public Optional<StoresListModel> storeFindByCode(Long storeCode){
        return adminMapper.storesFindByCode(storeCode);
    }

    public List<StockModel> stockFindAll(ItemCondition itemCondition){
        return adminMapper.stockFindAll(itemCondition);
    }
    public Optional<StockModel> stockFindByCode(Long itemCode){
        return adminMapper.stockFindByCode(itemCode);
    }

    public void updateItem(UpdateItemModel itemModel){
        adminMapper.updateItem(itemModel);
    }
    public void deleteStoresByCode(Long storeCode){
        adminMapper.deleteStoresByCode(storeCode);
    }

    public Optional<ItemQtyModel> itemFindByCode(Long itemCode){
        return adminMapper.itemFindByCode(itemCode);
    }

    public void addItemQty(ItemQtyModel item){
        adminMapper.addItemQty(item.getItemCode(), item.getStockQty());
    }
}
