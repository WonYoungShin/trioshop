package com.trioshop.repository.mybatis;

import com.trioshop.model.dto.item.ItemInfoByCart;
import com.trioshop.model.dto.item.ItemInfoByOrderList;
import com.trioshop.model.dto.item.ItemInfoByUser;
import com.trioshop.model.dto.item.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<ItemInfoByUser> findAllItem();
    List<ItemInfoByUser> searchItems(String searchText, String categoryName);
    List<CategoryEntity> categoryList();
    ItemInfoByUser itemInfoByCode(long itemCode);
    List<ItemInfoByCart> cartItemList(long userCode);
    List<ItemInfoByOrderList> orderList(long userCode);
}
