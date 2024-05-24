package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.PurchaseItemModel;
import com.trioshop.model.dto.admin.PurchaseListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.mybatis.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository("purchaseDao")
@RequiredArgsConstructor
public class PurchaseDaoImpl implements PurchaseDao{
    private final AdminMapper adminMapper;

    @Override
    public PurchaseItemModel save(PurchaseItemModel itemModel) {
        adminMapper.purchaseSave(itemModel);
        return itemModel;
    }

    @Override
    public List<PurchaseListModel> findAll(ItemCondition itemCondition) {
        return adminMapper.purchaseFindAll(itemCondition);
    }

    @Override
    public Optional<PurchaseListModel> findByCode(Long code) {
        return adminMapper.purchaseFindByCode(code);
    }

    @Override
    public void deleteByCode(Long code) throws Exception {
        adminMapper.deletePurchaseByCode(code);
    }
}
