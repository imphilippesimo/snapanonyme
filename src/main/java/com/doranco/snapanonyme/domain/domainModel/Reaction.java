package com.doranco.snapanonyme.domain.domainModel;

import java.util.Date;

public class Reaction {

    public enum ReactionType {
        LIKE, LOVE, LAUGN, DISGUTED
    }

    private int id;
    private Article article;
    private Comment comment;
    private User user;
    private Date date;
    private ReactionType reactionType;

    public Reaction() {
    }

    public Reaction(int id, Article article, Comment comment, User user, Date date, ReactionType reactionType) {
        this.id = id;
        this.article = article;
        this.comment = comment;
        this.user = user;
        this.date = date;
        this.reactionType = reactionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }
}
