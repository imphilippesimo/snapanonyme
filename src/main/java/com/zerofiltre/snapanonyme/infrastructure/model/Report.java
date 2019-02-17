package com.zerofiltre.snapanonyme.infrastructure.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class Report extends SuperClazz {

    @ManyToOne
    private User user;
    @ManyToOne
    private Article article;

    private String reason;
    private Instant Date;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Instant getDate() {
        return Date;
    }

    public void setDate(Instant date) {
        Date = date;
    }
}
