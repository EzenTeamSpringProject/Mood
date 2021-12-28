package com.ezen.mood.dto;

import com.ezen.mood.domain.review.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostFormDto {
    private String id;
    private String title;
    private String content;
    private List<MultipartFile> imageFiles;

    @Builder
    public PostFormDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .build();
    }
}
