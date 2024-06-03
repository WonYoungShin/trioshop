package com.trioshop.model.dto.admin;


import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddItemModel{
    private final String itemName;
    @Pattern(regexp = "10|20|30|40", message = "카테고리 코드를 정확히 입력해주세요!")
    private final String categoryCode;
    private final String factoryCode;
    private final String itemSize;
    private final String itemColor;
    private final Integer itemPrice;

    private Long itemCode;

}
