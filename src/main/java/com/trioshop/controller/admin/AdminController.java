package com.trioshop.controller.admin;

import com.trioshop.model.dto.item.ItemCondition;
import org.springframework.ui.Model;

import java.util.NoSuchElementException;


public interface AdminController<T, ID> {
    String savePage();
    String save(T itemModel);

    String findAll(ItemCondition itemCondition, Model model);

    String findByCode(ID code, Model model) throws NoSuchElementException;

}