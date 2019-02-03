package com.doranco.snapanonyme.domain.model;

public class Snap {

    private int id;
    private boolean isVisible;
    private int reportsNumber;
    private Picture picture;

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

    public Snap(int id, boolean isVisible, int reportsNumber, Picture picture) {
        this.id = id;
        this.isVisible = isVisible;
        this.reportsNumber = reportsNumber;
        this.picture = picture;
    }
}
