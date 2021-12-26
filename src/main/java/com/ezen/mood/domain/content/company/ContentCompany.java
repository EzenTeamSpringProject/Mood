package com.ezen.mood.domain.content.company;


import com.ezen.mood.domain.content.Content;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class ContentCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_company_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne
    @JoinColumn(name ="company_id")
    private Company company;

//    연관관계 편의 메소드 필요
    public void putContent(Content content) {
        this.content = content;
        content.getCompanies().add(this);
    }

    public void putCompany(Company company) {
        this.company = company;
        company.getContents().add(this);
    }

}
