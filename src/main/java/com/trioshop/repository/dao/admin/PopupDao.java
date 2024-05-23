package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.FactoryCondition;
import com.trioshop.model.dto.admin.FactoryEntity;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.model.dto.popup.PopupItemModel;
import com.trioshop.repository.mybatis.PopupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PopupDao {
    private final PopupMapper popupMapper;

    public List<PopupItemModel> findByAll(ItemCondition itemCondition){
        return popupMapper.findByAll(itemCondition);
    }
    public List<FactoryEntity> factoryFindByAll(FactoryCondition factoryCondition){
        return popupMapper.factoryFindAll(factoryCondition);
    }


}
