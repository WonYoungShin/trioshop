package com.trioshop.service.admin;

import com.github.pagehelper.PageInfo;
import com.trioshop.exception.ApplicationException;
import com.trioshop.exception.ExceptionType;
import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.StockModel;
import com.trioshop.model.dto.admin.UpdateItemModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.StockDao;
import com.trioshop.utils.service.PagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockDao stockDao;
    private final PagingService pagingService;

    @Transactional
    public void save(AddItemModel itemModel) {
        try{
             Long itemCode = stockDao.save(itemModel);
            System.out.println(itemCode);
            stockDao.stockSave(itemCode);
        } catch (Exception e){
            throw new ApplicationException(ExceptionType.DONT_SAVE);
        }
    }

    public PageInfo<StockModel> findAll(ItemCondition itemCondition, int page) {
        return pagingService.getPagedData(page, () -> stockDao.findAll(itemCondition));
    }


    public StockModel findByCode(Long code) {
        try{
            return stockDao.findByCode(code).orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
    }


    public void updateItem(UpdateItemModel itemModel){
        stockDao.updateItem(itemModel);
    }
}
