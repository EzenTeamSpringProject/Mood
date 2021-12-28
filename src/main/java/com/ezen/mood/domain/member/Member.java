package com.ezen.mood.domain.member;

import com.ezen.mood.config.auth.dto.SessionMember;

import com.ezen.mood.domain.review.Posts;
import com.ezen.mood.domain.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Posts> posts = new ArrayList<>();

    @OneToMany(mappedBy ="member")
    private List<WishContent> wishes = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member")
//    private List<Review> myReviews = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<LoveReview> likeReviews = new ArrayList<>();


    @PrePersist
    void prePersist(){
        if (name.equals("dokuny")) {
            role = Role.ADMIN;
        }
        if (role == null) {
            role = Role.ADMIN;
        }
    }

    @Builder
    public Member(String name, String password, String email, String picture, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public Member updateRelatedSns(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public Member update(String name, String password, String picture) {
        this.name = name;
        this.password = password;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public SessionMember toSessionMember() {
        return new SessionMember(this);
    }


}
