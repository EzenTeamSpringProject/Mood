package com.ezen.mood.domain.content.category;

import com.ezen.mood.domain.content.Content;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name="kr_name")
    private String krname;

    @Column(name="eng_name")
    private String engname;

    @OneToMany(mappedBy = "category")
    private List<Content> contentList = new ArrayList<>();

    public void addContent(Content content) {
        contentList.add(content);
        content.setCategory(this);
    }

    @Builder
    public Category(Long id, String krname, String engname, List<Content> contentList) {
        this.id = id;
        this.krname = krname;
        this.engname = engname;
        this.contentList = contentList;
    }
}

