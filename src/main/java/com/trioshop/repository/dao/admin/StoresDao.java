package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.ItemCodeAdjustModel;
import com.trioshop.model.dto.admin.StoreItemModel;
import com.trioshop.model.dto.admin.StoresListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.mybatis.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StoresDao{
    private final AdminMapper adminMapper;

    public void save(StoreItemModel itemModel) {
        adminMapper.storeSave(itemModel);
    }

    public List<StoresListModel> findAll(ItemCondition itemCondition) {
        return adminMapper.storesFindAll(itemCondition);
    }

    public Optional<StoresListModel> findByCode(Long code) {
        return adminMapper.storesFindByCode(code);    }

    public void deleteByCode(Long code){
        adminMapper.deleteStoresByCode(code);
    }

    public Optional<Integer> itemFindByCode(Long itemCode){
        return adminMapper.itemFindByCode(itemCode);
    }

    public void addItemQty(ItemCodeAdjustModel item){
        adminMapper.addItemQty(item.getItemCode(), item.getQuantity());
    }
}
