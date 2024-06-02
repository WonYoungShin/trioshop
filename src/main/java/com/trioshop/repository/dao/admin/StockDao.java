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
public class StockDao{
    private final AdminMapper adminMapper;

    public Long save(AddItemModel itemModel) {
        adminMapper.itemSave(itemModel);
        return itemModel.getItemCode();
    }

    public void stockSave(Long itemCode){
        adminMapper.stockSave(itemCode);
    }

    public List<StockModel> findAll(ItemCondition itemCondition) {
        return adminMapper.stockFindAll(itemCondition);
    }

    public Optional<StockModel> findByCode(Long code) {
        return adminMapper.stockFindByCode(code);
    }

    public void updateItem(UpdateItemModel itemModel){
        adminMapper.updateItem(itemModel);
    }

}
