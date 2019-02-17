package com.zerofiltre.snapanonyme.domain.repository;

import com.zerofiltre.snapanonyme.domain.model.Picture;

import java.util.List;

public interface Pictures {

    public List<Pictures> all();

    public Picture getById(int id);

    public Picture save(Picture picture);

    public Picture update(Picture picture);

    public void delete(int id);
}
