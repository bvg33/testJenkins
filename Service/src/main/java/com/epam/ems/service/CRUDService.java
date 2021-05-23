package com.epam.ems.service;

public interface CRUDService<T> extends CRDService<T> {
    void update(T entity, int id);
}
