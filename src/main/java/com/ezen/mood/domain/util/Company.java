package com.ezen.mood.domain.util;

import com.ezen.mood.domain.content.Content;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "company")
    private List<ContentCompany> contents = new ArrayList<>();
}
