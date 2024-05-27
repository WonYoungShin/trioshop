package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.ItemQtyModel;
import com.trioshop.model.dto.admin.StoreItemModel;
import com.trioshop.model.dto.admin.StoresListModel;

import java.util.Optional;

public interface StoresService extends AdminService<StoreItemModel, StoresListModel>{

    void addItemQty(ItemQtyModel item);
    Optional<ItemQtyModel> itemFindById(Long itemCode);
}
