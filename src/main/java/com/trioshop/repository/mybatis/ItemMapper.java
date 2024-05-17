package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.item.ItemInfoByUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<ItemInfoByUser> findAllItem();
}
