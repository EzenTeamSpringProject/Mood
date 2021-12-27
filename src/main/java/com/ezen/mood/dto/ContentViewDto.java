package com.ezen.mood.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class ContentViewDto {

    private Long id;

    private String title;

    private String description;

    @DateTimeFormat(pattern = "yyyy년 MM월 dd일")
    private LocalDate releaseDate;

    // 셀렉트
    private String rating;

    //   category를 데이터베이스에서 all로 가져오고 그 리스트를 셀렉트로 선택하면
    private String category;

    // 엔티티 가져와서 checkbox로 뿌려준다.
    private List<String> companies;

    private String cast;

    private String trailer;

    // 엔티티 가져와서 checkbox로 뿌려준다.
    private List<String> genres;

    @Builder
    public ContentViewDto(Long id, String title, String description, LocalDate releaseDate, String rating, String category, List<String> companies, String cast, String trailer, List<String> genres) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.category = category;
        this.companies = companies;
        this.cast = cast;
        this.trailer = trailer;
        this.genres = genres;
    }
}
