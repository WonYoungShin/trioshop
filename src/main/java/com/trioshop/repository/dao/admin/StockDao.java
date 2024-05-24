package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.StockModel;
import com.trioshop.model.dto.admin.UpdateItemModel;

public interface StockDao extends AdminDao<AddItemModel, StockModel> {

    void updateItem(UpdateItemModel itemModel);
}
