package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.ItemQtyModel;
import com.trioshop.model.dto.admin.StoreItemModel;
import com.trioshop.model.dto.admin.StoresListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.StoresDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoresServiceImpl implements StoresService{
    private final StoresDao storesDao;

    @Override
    public StoreItemModel save(StoreItemModel itemModel) {
        return storesDao.save(itemModel);
    }

    @Override
    public List<StoresListModel> findAll(ItemCondition itemCondition) {
        return storesDao.findAll(itemCondition);
    }

    @Override
    public Optional<StoresListModel> findByCode(Long code) {
        return storesDao.findByCode(code);
    }

    @Override
    public void deleteByCode(Long code) throws Exception {
        storesDao.deleteByCode(code);
    }
    public void addItemQty(ItemQtyModel item){
        storesDao.addItemQty(item);
    }
    public Optional<ItemQtyModel> itemFindById(Long itemCode){
        return storesDao.itemFindByCode(itemCode);
    }
}
