package com.trioshop.service.admin;

import com.trioshop.model.dto.popup.PopupItemModel;
import com.trioshop.repository.dao.admin.PopupDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopupService {
    private final PopupDao popupDao;

    public List<PopupItemModel> findByAll(){
        return popupDao.findByAll();
    }

}
