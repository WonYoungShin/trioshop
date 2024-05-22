package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.admin.FactoryEntity;
import com.trioshop.model.dto.popup.PopupItemModel;
import com.trioshop.repository.mybatis.PopupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PopupDao {
    private final PopupMapper popupMapper;

    public List<PopupItemModel> findByAll(){
        return popupMapper.findByAll();
    }
    public List<FactoryEntity> factoryFindByAll(){
        return popupMapper.factoryFindAll();
    }


}
