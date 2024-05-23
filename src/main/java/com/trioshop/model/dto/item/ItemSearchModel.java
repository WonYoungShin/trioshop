package com.trioshop.model.dto.item;

import com.trioshop.utils.Category;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchModel {
    String name;
    Category category;
}
