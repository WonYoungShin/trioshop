package com.trioshop.service.admin;

import com.trioshop.model.dto.item.ItemCondition;

import java.util.List;
import java.util.Optional;

public interface AdminService<T, L, ID> {

        T save(T itemModel);

        List<L> findAll(ItemCondition itemCondition);

        Optional<L> findByCode(ID code);

        void deleteByCode(ID code) throws Exception;

}
