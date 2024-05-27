package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.FactoryCondition;
import com.trioshop.model.dto.admin.FactoryEntity;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.model.dto.popup.PopupItemModel;
import com.trioshop.repository.dao.admin.PopupDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopupService {
    private final PopupDao popupDao;

    public List<PopupItemModel> findByAll(ItemCondition itemCondition){
        return popupDao.findByAll(itemCondition);
    }
    public List<FactoryEntity> factoryFindByAll(FactoryCondition factoryCondition){
        return popupDao.factoryFindByAll(factoryCondition);
    }

}
