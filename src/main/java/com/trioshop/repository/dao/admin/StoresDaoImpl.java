package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.ItemQtyModel;
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
public class StoresDaoImpl implements StoresDao {
    private final AdminMapper adminMapper;

    @Override
    public StoreItemModel save(StoreItemModel itemModel) {
        adminMapper.storeSave(itemModel);
        return itemModel;
    }

    @Override
    public List<StoresListModel> findAll(ItemCondition itemCondition) {
        return adminMapper.storesFindAll(itemCondition);
    }

    @Override
    public Optional<StoresListModel> findByCode(Long code) {
        return adminMapper.storesFindByCode(code);    }

    @Override
    public void deleteByCode(Long code) throws Exception {
        adminMapper.deleteStoresByCode(code);
    }

    @Override
    public Optional<ItemQtyModel> itemFindByCode(Long itemCode){
        return adminMapper.itemFindByCode(itemCode);
    }

    @Override
    public void addItemQty(ItemQtyModel item){
        adminMapper.addItemQty(item.getItemCode(), item.getStockQty());
    }
}
