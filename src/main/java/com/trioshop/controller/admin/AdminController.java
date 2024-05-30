package com.trioshop.controller.admin;

import com.trioshop.model.dto.item.ItemCondition;
import org.springframework.ui.Model;

import java.util.NoSuchElementException;

/***
 * @param <T> PurchaseItemModel, AddItemModel, StoreItemModel
 *     PurchaseController, StoresController, StockController
 */
public interface AdminController<T> {
    String savePage();
    String save(T itemModel);

    String findAll(ItemCondition itemCondition, Model model);

    String findByCode(Long code, Model model);

}