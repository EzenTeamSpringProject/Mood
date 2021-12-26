package com.ezen.mood.domain.reply;

import com.ezen.mood.domain.review.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
@DiscriminatorValue("R")
@NoArgsConstructor
@Getter
public class ReviewReply extends Reply{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Post post;


}
