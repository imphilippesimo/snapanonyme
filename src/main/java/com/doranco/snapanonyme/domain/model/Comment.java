package com.doranco.snapanonyme.domain.model;

public class Comment {

    private int id;
    private Article article;
    private String content;
    private Comment subComment;

    public Comment() {
    }

    public Comment(int id, Article article, String content, Comment subComment) {
        this.id = id;
        this.article = article;
        this.content = content;
        this.subComment = subComment;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment getSubComment() {
        return subComment;
    }

    public void setSubComment(Comment subComment) {
        this.subComment = subComment;
    }
}
