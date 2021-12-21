package com.ezen.mood.domain.content.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KIND {
    DRAMA("drama","드라마"),
    MOVIE("movie","영화"),
    ANIMATION("animation","애니메이션");

    private final String key;
    private final String value;
}
