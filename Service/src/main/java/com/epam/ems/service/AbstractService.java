package com.epam.ems.service;

import com.epam.ems.dao.CRDDao;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T> implements CRDService<T> {

    private static final String GET_BY_NAME_PART = "getByNamePart";

    private static final String GET_BY_TAG_NAME = "getByTagName";

    private final CRDDao<T> dao;

    public AbstractService(CRDDao<T> dao) {
        this.dao = dao;
    }

    @Override
    public List<T> getAll(int page, int elements) {
        return dao.getAll(page, elements);
    }

    @Override
    public T getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void deleteById(int id) {
        dao.removeById(id);
    }

    @Override
    public void insertIntoDB(T entity) {
        dao.insert(entity);
    }


    protected List<String> getAllParameters(String paramName, MultiValueMap<String, String> allRequestParams) {
        List<String> params = new ArrayList<>();
        if (allRequestParams.containsKey(paramName)) {
            params = allRequestParams.get(paramName);
        }
        return params;
    }

    protected String getParameter(String paramName, MultiValueMap<String, String> allRequestParams) {
        String param = new String();
        if (allRequestParams.containsKey(paramName)) {
            param = allRequestParams.get(paramName).get(0);
        }
        return param;
    }

    protected List<T> findByPartOfName(MultiValueMap<String, String> allRequestParams, int page, int elements) {
        String parameter = getParameter(GET_BY_NAME_PART, allRequestParams);
        List<T> entities = new ArrayList<>();
        if (!parameter.isEmpty()) {
            entities = dao.getByNamePart(parameter, page, elements);
        }
        return entities;
    }

    protected List<T> findByTagNames(MultiValueMap<String, String> allRequestParams, int page, int elements) {
        List<String> parameters = getAllParameters(GET_BY_TAG_NAME, allRequestParams);
        List<T> finalList = new ArrayList<>();
        if (!parameters.isEmpty()) {
            for (String parameter : parameters) {
                findCertificatesWithTags(parameter, finalList, page, elements);
            }
        }
        return finalList;
    }

    protected void fillListIfEmpty(List<T> foundByPartOfName, List<T> foundByTagNames, List<T> sortedByParam) {
        if (foundByTagNames.isEmpty() && !foundByPartOfName.isEmpty()) {
            foundByTagNames.addAll(foundByPartOfName);
        } else if (foundByPartOfName.isEmpty() && !foundByTagNames.isEmpty()) {
            foundByPartOfName.addAll(foundByTagNames);
        } else if (foundByPartOfName.isEmpty() && foundByTagNames.isEmpty()) {
            foundByPartOfName.addAll(sortedByParam);
            foundByTagNames.addAll(sortedByParam);
        }
    }

    private void findCertificatesWithTags(String parameter, List<T> finalList, int page, int elements) {
        List<T> entities = dao.getByTagName(parameter, page, elements);
        if (!finalList.isEmpty()) {
            finalList.retainAll(entities);
        } else {
            finalList.addAll(entities);
        }
    }


}
