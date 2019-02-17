package com.zerofiltre.snapanonyme.infrastructure.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class SubComment extends SuperClazz {

    @ManyToOne
    private User commentator;
    @ManyToOne
    private Comment commented;
    private String content;
    private Instant date;


    public User getCommentator() {
        return commentator;
    }

    public void setCommentator(User commentator) {
        this.commentator = commentator;
    }

    public Comment getCommented() {
        return commented;
    }

    public void setCommented(Comment commented) {
        this.commented = commented;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
