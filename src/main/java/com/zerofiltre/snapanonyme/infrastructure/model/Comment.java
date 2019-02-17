package com.zerofiltre.snapanonyme.infrastructure.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Entity
public class Comment extends SuperClazz {


    @ManyToOne
    private User commentator;
    private String content;
    private Instant date;
    @ManyToOne
    private Article commented;
    @OneToMany(mappedBy = "commented")
    private List<SubComment> comments;
    @OneToMany
    private List<Reaction> reactions;


    public User getCommentator() {
        return commentator;
    }

    public void setCommentator(User commentator) {
        this.commentator = commentator;
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

    public Article getCommented() {
        return commented;
    }

    public void setCommented(Article commented) {
        this.commented = commented;
    }

    public List<SubComment> getComments() {
        return comments;
    }

    public void setComments(List<SubComment> comments) {
        this.comments = comments;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }
}
