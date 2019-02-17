package com.zerofiltre.snapanonyme.presentation.dto;

import com.zerofiltre.snapanonyme.domain.model.Location;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.Instant;


public class SnapDTO {

    private int id;
    private boolean isVisible =true;
    private int reportsNumber =0;
    private PictureDTO picture;
    private Instant postedOn;
    private Location postedAt;

    public SnapDTO() {
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

    public PictureDTO getPicture() {
        return picture;
    }

    public void setPicture(PictureDTO picture) {
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
}
