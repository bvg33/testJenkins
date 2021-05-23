package com.epam.ems.dao;

import java.util.List;

public interface CRDDao<T> {

    T getById(int id);

    List<T> getAll(int page, int elements);

    void insert(T item);

    void removeById(int id);

    List<T> getByTagName(String name, int page, int elements);

    List<T> getEntitiesSortedByParameter(String sortType, String parameter, int page, int elements);

    List<T> getByNamePart(String parameter, int page, int elements);
}
