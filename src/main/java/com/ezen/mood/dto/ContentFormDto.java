package com.ezen.mood.dto;

import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.content.category.Category;
import com.ezen.mood.domain.content.enums.Rating;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
public class ContentFormDto {

    private Long id;

    private String title;

    private String description;

    private LocalDate releaseDate;

    // 셀렉트
    private Rating rating;

//   category를 데이터베이스에서 all로 가져오고 그 리스트를 셀렉트로 선택하면
    private Long category;

    // 엔티티 가져와서 checkbox로 뿌려준다.
    private List<Long> companies;

    private String cast;

    // 엔티티 가져와서 checkbox로 뿌려준다.
    private List<Long> genres;

    private List<MultipartFile> posters;

    public Content toEntity() {
        return Content.builder()
                .title(title)
                .description(description)
                .releaseDate(releaseDate)
                .rating(rating)
                .casts(cast)
                .build();
    }
}
