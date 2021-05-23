package com.epam.ems.service;

import org.springframework.util.MultiValueMap;

import java.util.List;

public interface CRDService<T> {

    List<T> getAll(int page, int elements);

    void insertIntoDB(T entity);

    T getById(int id);

    void deleteById(int id);

    List<T> doFilter(MultiValueMap<String, String> allRequestParams, int page, int elements);
}
