package com.trioshop.service.admin;

import com.github.pagehelper.PageInfo;
import com.trioshop.model.dto.admin.PurchaseItemModel;
import com.trioshop.model.dto.admin.PurchaseListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.PurchaseDao;
import com.trioshop.utils.service.PagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseDao purchaseDao;
    private final PagingService pagingService;

    public void save(PurchaseItemModel itemModel) { purchaseDao.save(itemModel);
    }

    public PageInfo<PurchaseListModel> findAll(ItemCondition itemCondition,int page) {
        return pagingService.getPagedData(page,()->purchaseDao.findAll(itemCondition));
    }

    public PurchaseListModel findByCode(Long code) {
        try{
            return purchaseDao.findByCode(code).orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
    }

    public void deleteByCode(Long code) {
        purchaseDao.deleteByCode(code);
    }
}
