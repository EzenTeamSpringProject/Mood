package com.ezen.mood.domain.member;

import com.ezen.mood.domain.content.Content;
import com.ezen.mood.domain.member.Member;

import javax.persistence.*;

//@Entity
@Table(name = "wish_content")
public class WishContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_content_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="content_id")
    private Content content;

}
