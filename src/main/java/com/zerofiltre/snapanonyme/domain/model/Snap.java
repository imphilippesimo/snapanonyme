package com.zerofiltre.snapanonyme.domain.model;

import java.time.Instant;

public class Snap {

    private int id;
    private boolean isVisible;
    private int reportsNumber;
    private Picture picture;
    private Instant postedOn;
    private Location postedAt;
    private double milesAway;



    public Snap() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getReportsNumber() {
        return reportsNumber;
    }

    public void setReportsNumber(int reportsNumber) {
        this.reportsNumber = reportsNumber;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Instant getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Instant postedOn) {
        this.postedOn = postedOn;
    }

    public Location getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Location postedAt) {
        this.postedAt = postedAt;
    }

    public Snap(int id, boolean isVisible, int reportsNumber, Picture picture) {
        this.id = id;
        this.isVisible = isVisible;
        this.reportsNumber = reportsNumber;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Snap{" +
                "id=" + id +
                ", isVisible=" + isVisible +
                ", reportsNumber=" + reportsNumber +
                ", picture=" + picture +
                ", postedOn=" + postedOn +
                ", postedAt=" + postedAt +
                '}';
    }

    public double getMilesAway() {
        return milesAway;
    }

    public void setMilesAway(double milesAway) {
        this.milesAway = milesAway;
    }
}
