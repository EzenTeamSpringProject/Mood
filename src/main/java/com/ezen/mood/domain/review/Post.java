package com.ezen.mood.domain.review;

import com.ezen.mood.domain.reply.ReviewReply;
import com.ezen.mood.domain.util.BaseTimeEntity;
import com.ezen.mood.domain.member.Member;
import com.ezen.mood.domain.reply.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
//@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false,length = 100)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Lob
    private String contents;  // 에디터용

//    @OneToMany(mappedBy = "review")
//    private List<ReviewReply> replies = new ArrayList<>();


    @Builder
    public Post(String title, Member member, String contents) {
        this.title = title;
        this.member = member;
        this.contents = contents;

//        member.getMyReviews().add(this);
    }
    // 리뷰가 작성될 때 멤버의 내 리뷰에도 등록이 되어야한다.
}
