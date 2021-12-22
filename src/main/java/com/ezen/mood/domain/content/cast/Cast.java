package com.ezen.mood.domain.content.cast;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cast_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "cast")
    private List<ContentCast> contents = new ArrayList<>();

}
