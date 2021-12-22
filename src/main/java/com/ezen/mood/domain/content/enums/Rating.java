package com.ezen.mood.domain.content.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Rating {
   RA("ALL","전체 이용가"),
   R12("12","12세 관람가"),
   R15("15","15세 관람가"),
   R18("18","청소년 관람불가");

   private final String key;
   private final String value;
}
