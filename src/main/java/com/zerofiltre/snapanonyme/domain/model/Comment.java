package com.zerofiltre.snapanonyme.domain.model;

public class Comment {

    private int id;
    private Snap snap;
    private String content;
    private Comment subComment;

    public Comment() {
    }

    public Comment(int id, Snap snap, String content, Comment subComment) {
        this.id = id;
        this.snap = snap;
        this.content = content;
        this.subComment = subComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Snap getSnap() {
        return snap;
    }

    public void setSnap(Snap snap) {
        this.snap = snap;
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
