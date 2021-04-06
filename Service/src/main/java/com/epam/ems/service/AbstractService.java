package com.epam.ems.service;

import com.epam.ems.dao.Dao;
import com.epam.ems.exceptions.DaoException;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T> implements Service<T> {

    private static final String GET_BY_NAME_PART = "getByNamePart";

    private static final String GET_BY_TAG_NAME = "getByTagName";

    private final Dao<T> dao;

    public AbstractService(Dao<T> dao) {
        this.dao = dao;
    }

    @Override
    public List<T> getAll() {
        return dao.getAll();
    }

    @Override
    public T getById(int id) throws Exception {
        return dao.getById(id);
    }

    @Override
    public void deleteById(int id) throws DaoException {
        dao.removeById(id);
    }

    protected String getFilteredList(String paramName, MultiValueMap<String, String> allRequestParams) {
        if (allRequestParams.containsKey(paramName)) {
            return allRequestParams.getFirst(paramName);
        }
        return new String();
    }

    protected List<T> findByPartOfName(MultiValueMap<String, String> allRequestParams) {
        String parameter = getFilteredList(GET_BY_NAME_PART, allRequestParams);
        if (!parameter.isEmpty()) {
            return dao.getByNamePart(parameter);
        } else {
            return new ArrayList<>();
        }
    }

    protected List<T> findByTagNames(MultiValueMap<String, String> allRequestParams) {
        String parameter = getFilteredList(GET_BY_TAG_NAME, allRequestParams);
        if (!parameter.isEmpty()) {
            return dao.getByTagName(parameter);
        } else {
            return new ArrayList<>();
        }
    }

    protected void fillListIfEmpty(List<T> foundByPartOfName, List<T> foundByTagNames, List<T> sortedByParam) {
        if (foundByTagNames.isEmpty() && !foundByPartOfName.isEmpty()) {
            foundByTagNames.addAll(foundByPartOfName);
        } else if (foundByPartOfName.isEmpty() && !foundByTagNames.isEmpty()) {
            foundByPartOfName.addAll(foundByTagNames);
        } else {
            foundByPartOfName.addAll(sortedByParam);
            foundByTagNames.addAll(sortedByParam);
        }
    }


}
