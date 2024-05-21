package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.popup.PopupItemModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PopupMapper {

    List<PopupItemModel> findByAll();
}
