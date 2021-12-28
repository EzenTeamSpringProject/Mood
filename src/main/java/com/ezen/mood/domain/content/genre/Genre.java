package com.ezen.mood.domain.content.genre;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column(name="genre_kr_name")
    private String krname;

    @Column(name="genre_eng_name")
    private String engname;

    @OneToMany(mappedBy = "genre")
    private List<ContentGenre> contents;

    @Builder
    public Genre(Long id, String krname, String engname, List<ContentGenre> contents) {
        this.id = id;
        this.krname = krname;
        this.engname = engname;
        this.contents = contents;
    }


}
