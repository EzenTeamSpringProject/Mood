package com.ezen.mood.domain.content.genre;

import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.content.company.Company;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class ContentGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_ott_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne
    @JoinColumn(name ="ott_id")
    private Genre genre;

    public void putContent(Content content) {
        this.content = content;
        content.getGenres().add(this);
    }

    public void putGerne(Genre genre) {
        this.genre = genre;
        genre.getContents().add(this);
    }
}
