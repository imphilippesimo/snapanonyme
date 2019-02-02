package com.doranco.snapanonyme.domain.domainModel;

public class Article {

    private int id;
    private boolean isVisible;
    private int reportsNumber;
    private File file;

    public Article() {
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Article(int id, boolean isVisible, int reportsNumber, File file) {
        this.id = id;
        this.isVisible = isVisible;
        this.reportsNumber = reportsNumber;
        this.file = file;
    }
}
