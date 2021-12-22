package com.ezen.mood.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    MEMBER("ROLE_MEMBER","유저"),
    ADMIN("ROLE_ADMIN","관리자");

    private final String key;
    private final String title;
}
