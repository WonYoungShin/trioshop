package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.PurchaseItemModel;
import com.trioshop.model.dto.admin.PurchaseListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.PurchaseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseDao purchaseDao;

    public void save(PurchaseItemModel itemModel) { purchaseDao.save(itemModel);
    }

    public List<PurchaseListModel> findAll(ItemCondition itemCondition) {
        return purchaseDao.findAll(itemCondition);
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
