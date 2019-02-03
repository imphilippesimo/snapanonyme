package com.doranco.snapanonyme.infrastructure.mapper;

import com.doranco.snapanonyme.domain.model.Picture;
import com.doranco.snapanonyme.infrastructure.model.File;

import java.util.ArrayList;
import java.util.List;

public class FilePictureMapper {

    private static FilePictureMapper mapper;


    private FilePictureMapper() {
    }

    public static FilePictureMapper getMapper() {
        if (mapper == null)
            mapper = new FilePictureMapper();
        return mapper;
    }

    public File toFile(Picture picture) {
        if (picture == null)
            return null;

        File file = new File();
        file.setId(picture.getId());
        file.setSize(picture.getSize());
        file.setMimeType(picture.getMimeType());
        file.setUrl(picture.getUrl());

        return file;

    }

    public Picture toPicture(File file) {
        if (file == null) {
            return null;
        }
        Picture picture = new Picture();
        picture.setId(file.getId());
        picture.setMimeType(file.getMimeType());
        picture.setUrl(file.getUrl());
        picture.setSize(file.getSize());
        return picture;
    }

    public List<Picture> toPictures(List<File> files) {
        List<Picture> pictures = new ArrayList<Picture>();
        for (File file : files)
            pictures.add(toPicture(file));

        return pictures;

    }


    public List<File> toFiles(List<Picture> pictures) {
        List<File> files = new ArrayList<File>();
        for (Picture picture : pictures)
            files.add(toFile(picture));
        return files;
    }
}