package com.doranco.snapanonyme.domain.repository;

import java.util.List;

public interface Repository<T> {

    public void save(T obj);

    public List<T> findAll();

    public T findOne(int id);

    public List<T> findByDesignation(String des);

    public void update(T obj);

    public void remove(int id);

}
