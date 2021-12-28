package com.ezen.mood.domain.content.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum Rating {
   RA("ALL","전체 이용가"),
   R12("12","12세 관람가"),
   R15("15","15세 관람가"),
   R18("18","청소년 관람불가");

   private final String key;
   private final String value;

   public static Rating toRating(String rating) {
      for (Rating value : values()) {
         if (value.getKey().equals(rating)) {
            return value;
         }
      }
      log.info("Error : String '{}' don't exist in Enum_Rating, so return null",rating);
      return null;
   }
}
