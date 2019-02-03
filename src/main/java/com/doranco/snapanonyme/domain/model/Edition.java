package com.doranco.snapanonyme.domain.model;

import java.util.Date;

public class Edition {

    private int id;
    private User user;
    private Snap snap;
    private Date date;

    public Edition() {
    }

    public Edition(int id, User user, Snap snap, Date date) {
        this.id = id;
        this.user = user;
        this.snap = snap;
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

    public Snap getSnap() {
        return snap;
    }

    public void setSnap(Snap snap) {
        this.snap = snap;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
