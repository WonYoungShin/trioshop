package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.ItemQtyModel;
import com.trioshop.model.dto.admin.StockModel;
import com.trioshop.model.dto.admin.UpdateItemModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.AdminDao;
import com.trioshop.repository.dao.admin.StockDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockAdminServiceImpl extends AbstractAdminService<AddItemModel, StockModel> implements StockService{

    private final StockDao stockDao;

    @Override
    public AddItemModel save(AddItemModel itemModel) {
        AddItemModel addItemModel =  super.save(itemModel);
        
        stockDao.stockSave(addItemModel.getItemCode());
        return addItemModel;
    }

    @Override
    protected AdminDao<AddItemModel, StockModel> adminDao() {
        return stockDao;
    }


    public void updateItem(UpdateItemModel itemModel){
        stockDao.updateItem(itemModel);
    }
}
