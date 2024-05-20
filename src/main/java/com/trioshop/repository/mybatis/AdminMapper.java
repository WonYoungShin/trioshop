package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.PurchaseItemModel;
import com.trioshop.model.dto.admin.StoreItemModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    void itemSave(AddItemModel ItemModel);
    void storeSave(StoreItemModel ItemModel);
    void purchaseSave(PurchaseItemModel ItemModel);
}
