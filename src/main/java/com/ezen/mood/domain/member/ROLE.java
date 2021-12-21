package com.ezen.mood.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ROLE {
    USER("ROLE_USER","유저"),
    ADMIN("ROLE_ADMIN","관리자");

    private final String key;
    private final String title;
}
