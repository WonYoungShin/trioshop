package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.ItemQtyModel;
import com.trioshop.model.dto.admin.StoreItemModel;
import com.trioshop.model.dto.admin.StoresListModel;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.AdminDao;
import com.trioshop.repository.dao.admin.PurchaseDao;
import com.trioshop.repository.dao.admin.StoresDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoresAdminServiceImpl extends AbstractAdminService<StoreItemModel, StoresListModel> implements StoresService {

    private final StoresDao storesDao;
    private final PurchaseAdminServiceImpl purchaseAdminServiceImpl;

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

    @Override
    @Transactional
    public StoreItemModel save(StoreItemModel itemModel) {
        try{
            Long purchaseCode = itemModel.getPurchaseCode();
            if(purchaseCode != 0){
                purchaseAdminServiceImpl.deleteByCode(purchaseCode);
            }
        } catch (Exception e) {
            log.info("error!!!, NoSearchPurchaseCode");
        }
        return super.save(itemModel);
    }
}
