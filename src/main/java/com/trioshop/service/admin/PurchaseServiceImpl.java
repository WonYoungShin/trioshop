package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.PurchaseItemModel;
import com.trioshop.model.dto.admin.PurchaseListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.PurchaseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseDao purchaseDao;

    @Override
    public PurchaseItemModel save(PurchaseItemModel itemModel) {
        return purchaseDao.save(itemModel);
    }

    @Override
    public List<PurchaseListModel> findAll(ItemCondition itemCondition) {
        return purchaseDao.findAll(itemCondition);
    }

    @Override
    public Optional<PurchaseListModel> findByCode(Long code) {
        return purchaseDao.findByCode(code);
    }

    @Override
    public void deleteByCode(Long code) throws Exception {
        purchaseDao.deleteByCode(code);
    }
}
