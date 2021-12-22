package com.ezen.mood.domain.content;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;


@Getter
public class ContentRegDto {

    private String title;

    private String posterPath;

    private String kind;

    private List<String> genres;

    private LocalDate releaseDate;

    private List<String> companies;

    private String rating;

    @Builder
    public ContentRegDto(String title, String posterPath, String kind, List<String> genres, LocalDate releaseDate, List<String> companies, String rating) {
        this.title = title;
        this.posterPath = posterPath;
        this.kind = kind;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.companies = companies;
        this.rating = rating;
    }

//    public Content toEntity() {
//        Content.builder()
//                .title(title)
//                .posterPath(posterPath)
//                .kind()
//                .releaseDate(releaseDate)
//                .
//
//    }
}
