package com.epam.ems.service.tag;

import com.epam.ems.dao.CRDDao;
import com.epam.ems.dto.Tag;
import com.epam.ems.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static com.epam.ems.dto.fields.Constant.SORT_BY_NAME;


@Service
public class TagServiceImpl extends AbstractService<Tag> {

    private CRDDao<Tag> dao;

    @Autowired
    public TagServiceImpl(CRDDao<Tag> dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public List<Tag> doFilter(MultiValueMap<String, String> allRequestParams, int page, int elements) {
        List<Tag> sortedByParam = getTagsSortedByNames(allRequestParams, page, elements);
        List<Tag> foundByTagNames = findByTagNames(allRequestParams, page, elements);
        List<Tag> foundByPartOfName = findByPartOfName(allRequestParams, page, elements);
        List<Tag> foundedTags = new ArrayList<>();
        fillListIfEmpty(foundByPartOfName, foundByTagNames, sortedByParam);
        foundByTagNames.stream().filter(foundByPartOfName::contains).
                forEach(foundedTags::add);
        if (!sortedByParam.isEmpty()) {
            sortedByParam.retainAll(foundedTags);
            foundedTags = sortedByParam;
        }
        return foundedTags;
    }

    private List<Tag> getTagsSortedByNames(MultiValueMap<String, String> allRequestParams, int page, int elements) {
        String sortType = getParameter(SORT_BY_NAME, allRequestParams);
        List<Tag> sortedList = new ArrayList<>();
        if (!sortType.isEmpty()) {
            sortedList = dao.getEntitiesSortedByParameter(sortType, SORT_BY_NAME, page, elements);
        }
        return sortedList;
    }
}
