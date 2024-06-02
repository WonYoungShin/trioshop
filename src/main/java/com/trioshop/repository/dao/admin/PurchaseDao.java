package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.PurchaseItemModel;
import com.trioshop.model.dto.admin.PurchaseListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.mybatis.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class PurchaseDao {
    private final AdminMapper adminMapper;

    public void save(PurchaseItemModel itemModel) {
        adminMapper.purchaseSave(itemModel);
    }

    public List<PurchaseListModel> findAll(ItemCondition itemCondition) {
        return adminMapper.purchaseFindAll(itemCondition);
    }

    public Optional<PurchaseListModel> findByCode(Long code) {
        return adminMapper.purchaseFindByCode(code);
    }

    public void deleteByCode(Long code) {
        adminMapper.deletePurchaseByCode(code);
    }
}
