package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.AddItemModel;
import com.trioshop.model.dto.admin.StockModel;
import com.trioshop.model.dto.admin.UpdateItemModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.mybatis.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StockDaoImpl implements StockDao{
    private final AdminMapper adminMapper;

    @Override
    public AddItemModel save(AddItemModel itemModel) {
        adminMapper.itemSave(itemModel);
        return itemModel;
    }
    @Override
    public void stockSave(Long itemCode){
        adminMapper.stockSave(itemCode);
    }

    @Override
    public List<StockModel> findAll(ItemCondition itemCondition) {
        return adminMapper.stockFindAll(itemCondition);
    }

    @Override
    public Optional<StockModel> findByCode(Long code) {
        return adminMapper.stockFindByCode(code);
    }

    @Override
    public void updateItem(UpdateItemModel itemModel){
        adminMapper.updateItem(itemModel);
    }

    @Override
    public void deleteByCode(Long code) throws Exception {
        return;
    }
}
