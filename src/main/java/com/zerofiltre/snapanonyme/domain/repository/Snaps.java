package com.zerofiltre.snapanonyme.domain.repository;

import com.zerofiltre.snapanonyme.domain.model.Snap;

import java.util.List;

public interface Snaps {

    public List<Snap> all();

    public Snap getById(int id);

    public List<Snap> getByUserId();

    public Snap save(Snap snap);

    public Snap update(Snap snap);

    public void delete(int id);

}
