package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FactoryEntity {
    private final String factoryCode;
    private final String factoryName;
    private final String factoryTel;
    private final String factoryAddr;
}
