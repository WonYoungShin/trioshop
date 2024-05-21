package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddItemQtyModel {
    private Long itemCode;
    private Integer stockQty;
}
