package com.ezen.mood.domain.content.review;


import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.member.Member;
import com.ezen.mood.domain.util.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OneLineReview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "one_line_review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private String contents;

    @Builder
    public OneLineReview(Content content, Member member, String contents) {
        this.content = content;
        this.member = member;
        this.contents = contents;
    }
}
