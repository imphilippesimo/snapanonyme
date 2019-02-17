package com.zerofiltre.snapanonyme.domain.model;

public class Picture {

    private int id;
    private String name;
    private long size;
    private String mimeType;
    private byte[] content;

    public Picture() {
    }

    public Picture(int id, int size, String mimeType) {
        this.id = id;
        this.size = size;
        this.mimeType = mimeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
