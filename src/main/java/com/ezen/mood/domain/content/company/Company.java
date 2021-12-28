package com.ezen.mood.domain.content.company;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(name="company_kr_name")
    private String krname;

    @Column(name="company_eng_name")
    private String engname;

    @OneToMany(mappedBy = "company")
    private List<ContentCompany> contents = new ArrayList<>();

    @Builder
    public Company(Long id, String krname, String engname, List<ContentCompany> contents) {
        this.id = id;
        this.krname = krname;
        this.engname = engname;
        this.contents = contents;
    }
}
