package com.doranco.snapanonyme.domain.model;

public class Picture {

    private int id;
    private String url;
    private int size;
    private String mimeType;

    public Picture() {
    }

    public Picture(int id, String url, int size, String mimeType) {
        this.id = id;
        this.url = url;
        this.size = size;
        this.mimeType = mimeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
