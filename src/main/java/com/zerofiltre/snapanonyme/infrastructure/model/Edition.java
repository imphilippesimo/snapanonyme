package com.zerofiltre.snapanonyme.infrastructure.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class Edition extends SuperClazz {

    @ManyToOne
    private User user;
    @ManyToOne
    private Article article;
    private Instant date;

    public Edition() {
    }


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

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
