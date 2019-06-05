package com.zerofiltre.snapanonyme.domain.model;

import java.util.Date;

public class Edition {

    private int id;
    private Snaper snaper;
    private Snap snap;
    private Date date;

    public Edition() {
    }

    public Edition(int id, Snaper snaper, Snap snap, Date date) {
        this.id = id;
        this.snaper = snaper;
        this.snap = snap;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Snaper getSnaper() {
        return snaper;
    }

    public void setSnaper(Snaper snaper) {
        this.snaper = snaper;
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
