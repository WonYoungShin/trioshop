package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.*;
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

//    public Optional<PurchaseListModel> purchaseFindCode(){
//
//    }
    public List<PurchaseListModel> purchaseFindAll(){
        return adminMapper.purchaseFindAll();
    }
    public Optional<PurchaseListModel> purchaseFindByCode(Long purchaseCode){
        return adminMapper.purchaseFindByCode(purchaseCode);
    }

    public List<StoresListModel> storesFindAll(){
        return adminMapper.storesFindAll();
    }

    public Optional<StoresListModel> storeFindByCode(Long storeCode){
        return adminMapper.storesFindByCode(storeCode);
    }

    public List<StockModel> stockFindAll(){
        return adminMapper.stockFindAll();
    }
    public Optional<AddItemQtyModel> itemFindByCode(Long itemCode){
        return adminMapper.itemFindByCode(itemCode);
    }

    public void addItemQty(AddItemQtyModel item){
        adminMapper.addItemQty(item.getItemCode(), item.getStockQty());
    }
}
