package com.ezen.mood.domain.review;

import com.ezen.mood.domain.content.poster.Poster;
import com.ezen.mood.domain.member.Member;
import com.ezen.mood.domain.util.BaseTimeEntity;

import com.ezen.mood.dto.ReviewFormDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter@Setter
@NoArgsConstructor
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false,length = 500)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="poster_id")
    private Poster poster;

    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    @Builder
    public Review(Long id, String title, String content, Member member, Poster poster) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
        this.poster = poster;
    }

    public ReviewFormDto toFormDto() {
        return ReviewFormDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }


}
