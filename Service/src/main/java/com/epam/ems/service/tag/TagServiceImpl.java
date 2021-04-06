package com.epam.ems.service.tag;

import com.epam.ems.dao.Dao;
import com.epam.ems.dto.Tag;
import com.epam.ems.logic.creator.Creator;
import com.epam.ems.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class TagServiceImpl extends AbstractService<Tag> {

    private Dao<Tag> dao;

    private static final String SORT_BY_NAME_PARAM = "sortByName";

    private Creator<Tag> creator;

    @Autowired
    public TagServiceImpl(Dao<Tag> dao, Creator<Tag> creator) {
        super(dao);
        this.dao = dao;
        this.creator=creator;
    }

    @Override
    public void update(MultiValueMap<String, String> allRequestParams) {
        Tag tag = creator.createObject(allRequestParams);
        dao.save(tag);
    }

    @Override
    public List<Tag> doFilter(MultiValueMap<String, String> allRequestParams) {
        List<Tag> sortedByParam = getTagsSortedByNames(allRequestParams);
        List<Tag> foundByTagNames = findByTagNames(allRequestParams);
        List<Tag> foundByPartOfName = findByPartOfName(allRequestParams);
        List<Tag> foundedTags = new ArrayList<>();
        fillListIfEmpty(foundByPartOfName, foundByTagNames,sortedByParam);
        foundByTagNames.stream().filter(foundByPartOfName::contains).
                forEach(foundedTags::add);
        if (sortedByParam.isEmpty()) {
            return foundedTags;
        }
        sortedByParam.retainAll(foundedTags);
        return sortedByParam;
    }

    private List<Tag> getTagsSortedByNames(MultiValueMap<String, String> allRequestParams) {
        String sortType = getFilteredList(SORT_BY_NAME_PARAM, allRequestParams);
        if (!sortType.isEmpty()) {
            return dao.getEntitiesSortedByParameter(sortType, SORT_BY_NAME_PARAM);
        } else {
            return new ArrayList<>();
        }
    }
}
