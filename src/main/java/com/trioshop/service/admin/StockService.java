package com.trioshop.service.admin;

import com.trioshop.controller.exception.DontSaveException;
import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.StockModel;
import com.trioshop.model.dto.admin.UpdateItemModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.StockDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockDao stockDao;

    @Transactional
    public void save(AddItemModel itemModel) {
        try{
             Long itemCode = stockDao.save(itemModel);
            System.out.println(itemCode);
            stockDao.stockSave(itemCode);
        } catch (Exception e){
            throw new DontSaveException();
        }
    }

    public List<StockModel> findAll(ItemCondition itemCondition) {
        return stockDao.findAll(itemCondition);
    }


    public Optional<StockModel> findByCode(Long code) {
        return stockDao.findByCode(code);
    }


    public void updateItem(UpdateItemModel itemModel){
        stockDao.updateItem(itemModel);
    }
}
