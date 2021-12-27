package com.ezen.mood.domain.content.poster;

import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.File;

@Slf4j
@Getter
@NoArgsConstructor
@Entity
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="files_id")
    private Long id;

    @Column
    private String orginalname;

    @Column(length = 150)
    private String filename;

    @Column(length = 300)
    private String filepath;

    @Builder
    public Poster(String orginalname, String filename, String filepath) {
        this.orginalname = orginalname;
        this.filename = filename;
        this.filepath = filepath;
    }


    public void deletePhysicFile() {
        File file = new File(filepath);
        log.info("file {}",file.getPath());
        if (file.exists()) {
            file.delete();
        }
    }

}
