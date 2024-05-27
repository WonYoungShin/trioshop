package com.trioshop.service.admin;

import com.trioshop.model.dto.item.ItemCondition;
import com.trioshop.repository.dao.admin.AdminDao;

import java.util.List;
import java.util.Optional;

public abstract class AbstractAdminService<T, L> implements AdminService<T, L> {

    protected abstract AdminDao<T, L> adminDao();

    @Override
    public T save(T itemModel) {
        return adminDao().save(itemModel);
    }

    @Override
    public List<L> findAll(ItemCondition itemCondition) {
        return adminDao().findAll(itemCondition);
    }

    @Override
    public Optional<L> findByCode(Long code) {
        return adminDao().findByCode(code);
    }

    @Override
    public void deleteByCode(Long code) throws Exception {
        adminDao().deleteByCode(code);
    }
}
