package com.ezen.mood.domain.util;

import com.ezen.mood.domain.content.Content;
import lombok.Getter;

import javax.persistence.*;

@Getter
//@Entity
public class ContentCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_company_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cast_id")
    private Company company;
}
