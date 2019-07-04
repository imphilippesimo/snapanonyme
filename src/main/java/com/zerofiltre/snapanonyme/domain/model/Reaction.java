package com.zerofiltre.snapanonyme.domain.model;

import java.util.Date;

public class Reaction {

    public enum ReactionType {
        LIKE, LOVE, LAUGH, DISGUSTED
    }

    private int id;
    private Snap snap;
    private Comment comment;
    private Snaper snaper;
    private Date date;
    private ReactionType reactionType;

    public Reaction() {
    }

    public Reaction(int id, Snap snap, Comment comment, Snaper snaper, Date date, ReactionType reactionType) {
        this.id = id;
        this.snap = snap;
        this.comment = comment;
        this.snaper = snaper;
        this.date = date;
        this.reactionType = reactionType;
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Snaper getSnaper() {
        return snaper;
    }

    public void setSnaper(Snaper snaper) {
        this.snaper = snaper;
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
