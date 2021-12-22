package com.ezen.mood.domain.util;


import com.ezen.mood.domain.content.Content;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
//@Entity
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "cast")
    private List<ContentCast> contents = new ArrayList<>();

}
