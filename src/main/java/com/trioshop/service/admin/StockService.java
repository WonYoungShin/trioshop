package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.ItemQtyModel;
import com.trioshop.model.dto.admin.StockModel;
import com.trioshop.model.dto.admin.UpdateItemModel;

import java.util.Optional;

public interface StockService extends AdminService<AddItemModel, StockModel> {
    void updateItem(UpdateItemModel itemModel);

}
