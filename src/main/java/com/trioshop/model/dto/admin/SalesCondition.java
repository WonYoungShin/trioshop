package com.trioshop.model.dto.admin;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SalesCondition {
    private final Integer year;
    @Nullable private final Integer month;
}
