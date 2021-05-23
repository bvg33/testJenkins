package com.epam.ems.dao;

public interface CRUDDao<T> extends CRDDao<T> {
    void update(T entity);
}
