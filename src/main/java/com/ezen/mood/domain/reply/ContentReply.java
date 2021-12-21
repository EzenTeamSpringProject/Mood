package com.ezen.mood.domain.reply;

import com.ezen.mood.domain.content.Content;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
@DiscriminatorValue("C")
@NoArgsConstructor
@Getter
public class ContentReply extends Reply {

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;
}
