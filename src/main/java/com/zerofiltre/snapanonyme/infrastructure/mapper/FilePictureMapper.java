package com.zerofiltre.snapanonyme.infrastructure.mapper;

import com.zerofiltre.snapanonyme.domain.model.Picture;
import com.zerofiltre.snapanonyme.infrastructure.model.File;

import java.util.ArrayList;
import java.util.Arrays;
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
        file.setName(picture.getName());
        file.setMimeType(picture.getMimeType());
        byte[] content = picture.getContent();
        if (content != null) {
            file.setContent(Arrays.copyOf(content, content.length));
        }
        return file;

    }

    public Picture toPicture(File file) {
        if (file == null) {
            return null;
        }
        Picture picture = new Picture();
        picture.setName(file.getName());
        picture.setId(file.getId());
        picture.setMimeType(file.getMimeType());
        picture.setSize(file.getSize());
        byte[] content = file.getContent();
        if (content != null) {
            picture.setContent(Arrays.copyOf(content, content.length));
        }

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