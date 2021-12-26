package com.ezen.mood.domain.content;

import com.ezen.mood.domain.content.company.ContentCompany;
import com.ezen.mood.domain.content.category.Category;
import com.ezen.mood.domain.content.genre.ContentGenre;
import com.ezen.mood.domain.util.BaseTimeEntity;
import com.ezen.mood.domain.content.enums.Rating;

import com.ezen.mood.domain.content.poster.Poster;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private String description;

    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private Long love;

    @OneToMany(mappedBy = "content",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Poster> poster = new ArrayList<>();

    @OneToMany(mappedBy = "content")
    private List<ContentCompany> companies = new ArrayList<>();

    private String casts;

    @OneToMany(mappedBy = "content")
    private List<ContentGenre> genres = new ArrayList<>();
//
//    @OneToMany(mappedBy = "content")
//    private List<OneLineReview> replies = new ArrayList<>();

    @Builder
    public Content(Long id, String title, String description, LocalDate releaseDate, Rating rating, Category category, Long love, List<Poster> poster, List<ContentCompany> companies, String casts, List<ContentGenre> genres) {
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
    }
    //    편의 메서드



}
