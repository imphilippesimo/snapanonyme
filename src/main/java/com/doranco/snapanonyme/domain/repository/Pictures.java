package com.doranco.snapanonyme.domain.repository;

import com.doranco.snapanonyme.domain.model.Picture;
import com.doranco.snapanonyme.domain.model.Snap;

import java.util.List;

public interface Pictures {

    public List<Pictures> all();

    public Picture getById(int id);

    public void save(Picture picture);

    public Picture update(Picture picture);

    public void delete(int id);
}
