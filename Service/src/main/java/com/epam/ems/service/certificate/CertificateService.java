package com.epam.ems.service.certificate;

import com.epam.ems.dao.Dao;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import com.epam.ems.logic.creator.Creator;
import com.epam.ems.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class CertificateService extends AbstractService<Certificate> {

    private Dao<Certificate> dao;

    private static final String SORT_BY_NAME_PARAM = "sortByName";
    private static final String SORT_BY_DATE_PARAM = "sortByDate";

    @Autowired
    private Dao<Tag> tagDao;

    @Autowired
    private Creator<Certificate> creator;

    @Autowired
    public CertificateService(Dao<Certificate> dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public void update(MultiValueMap<String, String> allRequestParams) {
        Certificate certificate = creator.createObject(allRequestParams);
        List<Tag> requestTags = certificate.getTags();
        List<Tag> createdTags = tagDao.getAll();
        saveNewTags(requestTags, createdTags);
        dao.save(certificate);
    }

    @Override
    public List<Certificate> doFilter(MultiValueMap<String, String> allRequestParams) {
        List<Certificate> sortedByParam = getListSortedByParameter(allRequestParams);
        List<Certificate> foundByTagNames = findByTagNames(allRequestParams);
        List<Certificate> foundByPartOfName = findByPartOfName(allRequestParams);
        List<Certificate> foundedCertificates = new ArrayList<>();
        fillListIfEmpty(foundByPartOfName, foundByTagNames,sortedByParam);
        foundByTagNames.stream().filter(foundByPartOfName::contains).
                forEach(foundedCertificates::add);
        if (sortedByParam.isEmpty()) {
            return foundedCertificates;
        }
        sortedByParam.retainAll(foundedCertificates);
        return sortedByParam;
    }

    private void saveNewTags(List<Tag> requestTags, List<Tag> createdTags) {
        if (requestTags != null) {
            requestTags.stream().filter(tag->!createdTags.contains(tag)).forEach(tag -> tagDao.save(tag));
        }
    }

    private List<Certificate> getListSortedByParameter(MultiValueMap<String, String> allRequestParams) {
        String sortType;
        if (allRequestParams.containsKey(SORT_BY_NAME_PARAM)) {
            sortType = getFilteredList(SORT_BY_NAME_PARAM, allRequestParams);
            return dao.getEntitiesSortedByParameter(sortType, SORT_BY_NAME_PARAM);
        } else if (allRequestParams.containsKey(SORT_BY_DATE_PARAM)) {
            sortType = getFilteredList(SORT_BY_DATE_PARAM, allRequestParams);
            return dao.getEntitiesSortedByParameter(sortType, SORT_BY_DATE_PARAM);
        }
        return new ArrayList<>();
    }

}
