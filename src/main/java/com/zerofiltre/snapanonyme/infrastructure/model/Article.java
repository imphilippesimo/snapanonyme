package com.zerofiltre.snapanonyme.infrastructure.model;

import org.hibernate.annotations.Filter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ArticleFileMapping",
                entities = {
                        @EntityResult(
                                entityClass = Article.class,
                                fields = {
                                        @FieldResult(name = "postedOn", column = "posted_on"),
                                        @FieldResult(name = "isVisible", column = "is_visible"),
                                        @FieldResult(name = "reportsNumber", column = "reports_number"),
                                        @FieldResult(name = "file", column = "file_id")}),
                        @EntityResult(
                                entityClass = File.class,
                                fields = {
                                        @FieldResult(name = "id", column = "fileId"),
                                        @FieldResult(name = "mimeType", column = "mime_type")
                                }
                        )
                }
        )
})

public class Article extends SuperClazz {

    private boolean isVisible;
    private int reportsNumber;
    @OneToOne(cascade = CascadeType.PERSIST)
    private File file;

    @OneToMany(mappedBy = "article", cascade = CascadeType.PERSIST)
    private List<Reaction> reactions;

    @OneToMany(mappedBy = "commented", cascade = CascadeType.PERSIST)
    private List<Comment> comments;
    private Instant postedOn;
    private double longitude;
    private double latitude;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Instant getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Instant postedOn) {
        this.postedOn = postedOn;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }
}
