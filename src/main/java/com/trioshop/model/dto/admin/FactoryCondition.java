package com.trioshop.model.dto.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class FactoryCondition {
    private final String factoryCode;
    private final String factoryName;
}
