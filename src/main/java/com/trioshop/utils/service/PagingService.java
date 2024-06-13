package com.trioshop.utils.service;

import com.trioshop.model.dto.item.ItemInfoByUser;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@Service
public class PagingService {

        public <T> PageInfo<T> getPagedData(int page,  Supplier<List<T>> dataSupplier) {
            int size = 10;
            PageHelper.startPage(page, size);
            List<T> dataList = dataSupplier.get();
            if (dataList == null) {
                dataList = Collections.emptyList();
            }
            return new PageInfo<>(dataList);
        }

    public <T> PageInfo<T> getPagedData(int page, int size, Supplier<List<T>> dataSupplier) {
        PageHelper.startPage(page, size);
        List<T> dataList = dataSupplier.get();
        if (dataList == null) {
            dataList = Collections.emptyList();
        }
        return new PageInfo<>(dataList);
    }


}
