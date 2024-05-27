package com.trioshop.service.admin;

import com.trioshop.model.dto.admin.PurchaseItemModel;
import com.trioshop.model.dto.admin.PurchaseListModel;
import com.trioshop.repository.dao.admin.AdminDao;
import com.trioshop.repository.dao.admin.PurchaseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PurchaseAdminServiceImpl extends AbstractAdminService<PurchaseItemModel, PurchaseListModel> implements PurchaseService {

    private final PurchaseDao purchaseDao;

    @Override
    protected AdminDao<PurchaseItemModel, PurchaseListModel> adminDao() {
        return purchaseDao;
    }
}
