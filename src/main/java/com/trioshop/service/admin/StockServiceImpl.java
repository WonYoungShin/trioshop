package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.ItemQtyModel;
import com.trioshop.model.dto.admin.StockModel;
import com.trioshop.model.dto.admin.UpdateItemModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.StockDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService{
    private final StockDao stockDao;

    @Override
    public AddItemModel save(AddItemModel itemModel) {
        return stockDao.save(itemModel);
    }

    @Override
    public List<StockModel> findAll(ItemCondition itemCondition) {
        return stockDao.findAll(itemCondition);
    }

    @Override
    public Optional<StockModel> findByCode(Long code) {
        return stockDao.findByCode(code);
    }

    @Override
    public void deleteByCode(Long code) throws Exception {
        return;
    }
    @Override
    public void updateItem(UpdateItemModel itemModel){
        stockDao.updateItem(itemModel);
    }

}
