package com.trioshop.service.admin;

import com.github.pagehelper.PageInfo;
import com.trioshop.model.dto.admin.FactoryCondition;
import com.trioshop.model.dto.admin.FactoryEntity;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.model.dto.popup.PopupItemModel;
import com.trioshop.repository.dao.admin.PopupDao;
import com.trioshop.utils.service.PagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopupService {
    private final PopupDao popupDao;
    private final PagingService pagingService;

    public PageInfo<PopupItemModel> findByAll(ItemCondition itemCondition, int page){
        return pagingService.getPagedData(page,()->popupDao.findByAll(itemCondition));
    }
    public PageInfo<FactoryEntity> factoryFindByAll(FactoryCondition factoryCondition, int page){
        return pagingService.getPagedData(page,()->popupDao.factoryFindByAll(factoryCondition));
    }

}
