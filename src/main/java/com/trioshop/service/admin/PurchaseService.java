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

    public Optional<PurchaseListModel> findByCode(Long code) {
        return purchaseDao.findByCode(code);
    }

    public void deleteByCode(Long code) {
        purchaseDao.deleteByCode(code);
    }
}
