package com.trioshop.repository.dao.admin;

import com.trioshop.model.dto.item.ItemCondition;

import java.util.List;
import java.util.Optional;

public interface AdminDao <T, L> {

        T save(T itemModel);

        List<L> findAll(ItemCondition itemCondition);

        Optional<L> findByCode(Long code);

        void deleteByCode(Long code) throws Exception;

    }

