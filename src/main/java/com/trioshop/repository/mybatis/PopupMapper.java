package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.admin.FactoryCondition;
import com.trioshop.model.dto.admin.FactoryEntity;
import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.model.dto.popup.PopupItemModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PopupMapper {

    List<PopupItemModel> findByAll(ItemCondition itemCondition);
    List<FactoryEntity> factoryFindAll(FactoryCondition factoryCondition);
}
