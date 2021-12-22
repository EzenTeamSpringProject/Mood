package com.ezen.mood.domain.util;


import com.ezen.mood.domain.content.Content;
import lombok.Getter;

import javax.persistence.*;

@Getter
//@Entity
public class ContentCast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_cast_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne
    @JoinColumn(name = "cast_id")
    private Cast cast;

}
