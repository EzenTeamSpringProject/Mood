package com.ezen.mood.dto;

import com.ezen.mood.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReviewFormDto {
    private Long id;
    private String title;
    private String content;
    private MultipartFile image;

    public Review toEntity() {
        return Review.builder()
                .title(title)
                .content(content)
                .build();
    }

    @Builder
    public ReviewFormDto(Long id, String title, String content, MultipartFile image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
    }
}
