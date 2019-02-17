package com.zerofiltre.snapanonyme.infrastructure.model;


import javax.persistence.*;

@Entity
@Table(name = "file")
public class File extends SuperClazz {

    private long size;
    private String name;
    private String mimeType;
    @Lob
    @Column(length = 100000)
    private byte[] content;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
