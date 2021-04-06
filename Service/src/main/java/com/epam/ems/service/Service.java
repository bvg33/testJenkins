package com.epam.ems.service;

import com.epam.ems.exceptions.DaoException;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface Service<T> {
    void update(MultiValueMap<String, String> allRequestParams);

    List<T> getAll();

    T getById(int id) throws Exception;

    void deleteById(int id) throws DaoException;

    List<T> doFilter(MultiValueMap<String, String> allRequestParams);
}
