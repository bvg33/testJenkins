package com.epam.ems.dao;

import com.epam.ems.exceptions.DaoException;

import java.util.List;

public interface Dao<T> {

    T getById(int id) throws Exception;

    List<T> getAll();

    void save(T item);

    void removeById(int id) throws DaoException;

    List<T> getByTagName(String name);

    List<T> getEntitiesSortedByParameter(String sortType, String parameter);

    List<T> getByNamePart(String parameter);
}
