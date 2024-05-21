package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.item.ItemInfoByUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<ItemInfoByUser> findAllItem();
    List<ItemInfoByUser> searchItems(String searchText, String categoryName);
    List<String> categoryList();
}
