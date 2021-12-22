package com.ezen.mood.domain.content.genre;

import com.ezen.mood.domain.content.enums.GenreName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private GenreName genreName;

    @OneToMany(mappedBy = "genre")
    private List<ContentGenre> contentGenres = new ArrayList<>();


}
