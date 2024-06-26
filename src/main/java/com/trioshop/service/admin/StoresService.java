package com.trioshop.service.admin;

import com.github.pagehelper.PageInfo;
import com.trioshop.exception.ApplicationException;

import com.trioshop.exception.ExceptionType;
import com.trioshop.exception.QuantityAdjustFailed;
import com.trioshop.model.dto.admin.ItemCodeAdjustModel;
import com.trioshop.model.dto.admin.StoreItemModel;
import com.trioshop.model.dto.admin.StoresListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.StoresDao;
import com.trioshop.utils.service.PagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoresService{

    private final StoresDao storesDao;
    private final PurchaseService purchaseService;
    private final PagingService pagingService;

    private  enum Status{
        ADD, MINUS
    }
    @Transactional
    public void save(StoreItemModel itemModel){
        try {
            adjustItemQty(itemModel.getItemCode(), itemModel.getStoreQty(), Status.ADD);
            storesDao.save(itemModel);
            Long purchaseCode = itemModel.getPurchaseCode();
            if (purchaseCode != 0L) {
                purchaseService.deleteByCode(purchaseCode);
            }
        } catch (Exception e){
            throw new ApplicationException(ExceptionType.DONT_SAVE);
        }

    }
    private void adjustItemQty(Long itemCode, Integer qty, Status status) {
        if(status==Status.ADD){
            qty += itemFindById(itemCode);
        }
        if(status==Status.MINUS){
            qty = itemFindById(itemCode) - qty;
        }

        ItemCodeAdjustModel buildItem = new ItemCodeAdjustModel(itemCode,qty);
        storesDao.addItemQty(buildItem);
    }

    public int itemFindById(Long itemCode){
        Optional<Integer> qty = storesDao.itemFindByCode(itemCode);
        return qty.orElseThrow(NoSuchElementException::new);
    }

    public PageInfo<StoresListModel> findAll(ItemCondition itemCondition, int page) {
        return pagingService.getPagedData(page, () ->storesDao.findAll(itemCondition));
    }

    public StoresListModel findByCode(Long code) {
        try{
            return storesDao.findByCode(code).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public void deleteByCode(Long code) throws RuntimeException{
       if (!delStockQty(code)) {
           throw new QuantityAdjustFailed();
       }
       storesDao.deleteByCode(code);
    }

    private boolean delStockQty(Long delStoreCode) {
        try {
            StoresListModel storeItem = storesDao.findByCode(delStoreCode)
                    .orElseThrow(() -> new NoSuchElementException("Store item not found for code: " + delStoreCode));
            adjustItemQty(storeItem.getItemCode(), storeItem.getStoresQty(), Status.MINUS);
        }catch (NoSuchElementException e){
            return false;
        }
        return true;
    }
}
