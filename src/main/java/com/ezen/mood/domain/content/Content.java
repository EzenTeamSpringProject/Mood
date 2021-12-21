package com.ezen.mood.domain.content;

import com.ezen.mood.domain.content.enums.KIND;
import com.ezen.mood.domain.content.genre.ContentGenre;
import com.ezen.mood.domain.reply.ContentReply;
import com.ezen.mood.domain.util.BaseTimeEntity;
import com.ezen.mood.domain.content.enums.RATING;
import com.ezen.mood.domain.util.ContentCast;
import com.ezen.mood.domain.util.ContentCompany;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
//@Entity
public class Content extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private long id;

    private String title;

    @OneToMany(mappedBy = "content")
    private List<Poster> posters = new ArrayList<>();

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private RATING rating;

    @Enumerated(EnumType.STRING)
    private KIND kind;


    private Long like;

    @OneToMany(mappedBy = "content")
    private List<ContentCompany> providers = new ArrayList<>();

    @OneToMany(mappedBy = "content")
    private List<ContentCast> casts = new ArrayList<>();

    @OneToMany(mappedBy = "content")
    private List<ContentGenre> contentGenres = new ArrayList<>();

    @OneToMany(mappedBy = "content")
    private List<ContentReply> replies = new ArrayList<>();


}
