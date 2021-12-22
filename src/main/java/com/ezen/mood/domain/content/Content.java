package com.ezen.mood.domain.content;

import com.ezen.mood.domain.content.company.ContentCompany;
import com.ezen.mood.domain.content.enums.Kind;
import com.ezen.mood.domain.content.genre.ContentGenre;
import com.ezen.mood.domain.content.review.OneLineReview;
import com.ezen.mood.domain.util.BaseTimeEntity;
import com.ezen.mood.domain.content.enums.Rating;
import com.ezen.mood.domain.content.cast.ContentCast;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Content extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rating rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Kind kind;

    @Column
    private Long love;

    @Column
    private String posterPath;

    @OneToMany(mappedBy = "content")
    private List<ContentCompany> providers = new ArrayList<>();

    @OneToMany(mappedBy = "content")
    private List<ContentCast> casts = new ArrayList<>();

    @OneToMany(mappedBy = "content")
    private List<ContentGenre> contentGenres = new ArrayList<>();

    @OneToMany(mappedBy = "content")
    private List<OneLineReview> replies = new ArrayList<>();


    @Builder
    public Content(String title, String description, LocalDate releaseDate, Rating rating, Kind kind, Long love, String posterPath) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.kind = kind;
        this.love = love;
        this.posterPath = posterPath;
    }
}
