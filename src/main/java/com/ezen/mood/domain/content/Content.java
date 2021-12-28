package com.ezen.mood.domain.content;

import com.ezen.mood.domain.content.company.ContentCompany;
import com.ezen.mood.domain.content.category.Category;
import com.ezen.mood.domain.content.genre.ContentGenre;
import com.ezen.mood.domain.util.BaseTimeEntity;
import com.ezen.mood.domain.content.enums.Rating;

import com.ezen.mood.domain.content.poster.Poster;
import com.ezen.mood.dto.ContentFormDto;
import com.ezen.mood.dto.ContentViewDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@Entity
public class Content extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private Long love;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="poster_id")
    private Poster poster;

    @OneToMany(mappedBy = "content")
    private List<ContentCompany> companies = new ArrayList<>();

    private String casts;

    private String trailer;

    @OneToMany(mappedBy = "content")
    private List<ContentGenre> genres = new ArrayList<>();
//
//    @OneToMany(mappedBy = "content")
//    private List<OneLineReview> replies = new ArrayList<>();

    @Builder
    public Content(Long id, String title, String description, LocalDate releaseDate, Rating rating, Category category, Long love, Poster poster, List<ContentCompany> companies, String casts, List<ContentGenre> genres,String trailer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.category = category;
        this.love = love;
        this.poster = poster;
        this.companies = companies;
        this.casts = casts;
        this.genres = genres;
        this.trailer= trailer;
    }
    //    편의 메서드


    public Poster getFirstPoster() {
        return this.poster;
    }

    public ContentViewDto toViewDto() {
      return ContentViewDto.builder()
              .id(id)
                .title(title)
                .description(description)
                .releaseDate(releaseDate)
              .trailer(trailer)
                .rating(rating.getValue())
                .category(category.getKrname())
                .companies(companies.stream().map(contentCompany -> contentCompany.getCompany().getKrname()).collect(Collectors.toList()))
                .cast(casts)
                .genres(genres.stream().map(contentGenre -> contentGenre.getGenre().getKrname()).collect(Collectors.toList()))
                .build();
    }

    public ContentFormDto toFormDto() {
        return ContentFormDto.builder()
                .id(id)
                .title(title)
                .description(description)
                .trailer(trailer)
                .releaseDate(releaseDate)
                .rating(rating)
                .category(category.getId())
                .companies(companies.stream().map(contentCompany -> contentCompany.getId()).collect(Collectors.toList()))
                .cast(casts)
                .genres(genres.stream().map(contentGenre -> contentGenre.getId()).collect(Collectors.toList()))
                .build();
    }
}
