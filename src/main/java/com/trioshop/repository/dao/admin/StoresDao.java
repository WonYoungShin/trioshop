package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.ItemQtyModel;
import com.trioshop.model.dto.admin.StoreItemModel;
import com.trioshop.model.dto.admin.StoresListModel;
import java.util.Optional;

public interface StoresDao extends AdminDao<StoreItemModel, StoresListModel>{


    Optional<ItemQtyModel> itemFindByCode(Long itemCode);

    void addItemQty(ItemQtyModel item);
}
