package com.ezen.mood.util;

import com.ezen.mood.domain.review.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;


@Getter
@NoArgsConstructor
@Entity
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="files_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Posts post;

    @Column
    private String orginalname;

    @Column(length = 150)
    private String filename;

    @Column(length = 300)
    private String filepath;

    @Builder
    public Files(Posts post, String orginalname, String filename, String filepath) {
        this.post = post;
        this.orginalname = orginalname;
        this.filename = filename;
        this.filepath = filepath;
    }

    public void setPost(Posts post) {
        this.post = post;
    }


    public void deletePhysicFile() {
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
    }
}
