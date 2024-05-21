package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.admin.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper {

    void itemSave(AddItemModel ItemModel);
    void storeSave(StoreItemModel ItemModel);
    void purchaseSave(PurchaseItemModel ItemModel);

    Optional<PurchaseListModel> purchaseFindCode();
    List<PurchaseListModel> purchaseFindAll();
    List<StoresListModel> storesFindAll();

}
