package com.ezen.mood.domain.content.poster;

import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="files_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="content_id")
    private Content content;

    @Column
    private String orginalname;

    @Column(length = 150)
    private String filename;

    @Column(length = 300)
    private String filepath;

    @Builder
    public Poster(Content content, String orginalname, String filename, String filepath) {
        this.content = content;
        this.orginalname = orginalname;
        this.filename = filename;
        this.filepath = filepath;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}
