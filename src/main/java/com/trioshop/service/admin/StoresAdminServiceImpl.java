package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.ItemQtyModel;
import com.trioshop.model.dto.admin.StoreItemModel;
import com.trioshop.model.dto.admin.StoresListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.AdminDao;
import com.trioshop.repository.dao.admin.StoresDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoresAdminServiceImpl extends AbstractAdminService<StoreItemModel, StoresListModel> implements StoresService {

    private final StoresDao storesDao;

    @Override
    protected AdminDao<StoreItemModel, StoresListModel> adminDao() {
        return storesDao;
    }

    @Override
    public void addItemQty(ItemQtyModel item){
        storesDao.addItemQty(item);
    }

    @Override
    public Optional<ItemQtyModel> itemFindById(Long itemCode){
        return storesDao.itemFindByCode(itemCode);
    }
}
