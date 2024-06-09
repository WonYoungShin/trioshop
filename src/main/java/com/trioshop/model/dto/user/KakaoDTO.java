package com.trioshop.model.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
public class KakaoDTO {
    private final String email;
    private final String nickname;
}
