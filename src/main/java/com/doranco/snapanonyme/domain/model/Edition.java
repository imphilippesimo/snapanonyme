package com.doranco.snapanonyme.domain.model;

import java.util.Date;

public class Edition {

    private int id;
    private User user;
    private Article article;
    private Date date;

    public Edition() {
    }

    public Edition(int id, User user, Article article, Date date) {
        this.id = id;
        this.user = user;
        this.article = article;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
