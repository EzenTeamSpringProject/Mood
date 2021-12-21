package com.ezen.mood.domain.content.genre;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
//@Entity
public class Genre {

    @Id
    @GeneratedValue
    @Column(name = "genre_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private GenreName genreName;

    @OneToMany(mappedBy = "genre")
    private List<ContentGenre> contentGenres = new ArrayList<>();


}
