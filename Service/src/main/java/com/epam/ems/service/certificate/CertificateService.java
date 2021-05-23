package com.epam.ems.service.certificate;

import com.epam.ems.dao.CRUDDao;
import com.epam.ems.dto.Certificate;
import com.epam.ems.logic.creator.Renovator;
import com.epam.ems.service.AbstractService;
import com.epam.ems.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

import static com.epam.ems.dto.fields.Constant.SORT_BY_DATE;
import static com.epam.ems.dto.fields.Constant.SORT_BY_NAME;


@Validated
@Service
public class CertificateService extends AbstractService<Certificate> implements CRUDService<Certificate> {

    private CRUDDao<Certificate> dao;
    private Renovator<Certificate> renovator;

    @Autowired
    public CertificateService(CRUDDao<Certificate> dao, Renovator<Certificate> renovator) {
        super(dao);
        this.dao = dao;
        this.renovator = renovator;
    }


    @Override
    public void update(Certificate certificate, int id) {
        Certificate oldCertificate = dao.getById(id);
        Certificate newCertificate = renovator.updateObject(certificate, oldCertificate);
        dao.update(newCertificate);
    }

    @Override
    public List<Certificate> doFilter(MultiValueMap<String, String> allRequestParams, int page, int elements) {
        List<Certificate> sortedByParam = getListSortedByParameter(allRequestParams, page, elements);
        List<Certificate> foundByTagNames = findByTagNames(allRequestParams, page, elements);
        List<Certificate> foundByPartOfName = findByPartOfName(allRequestParams, page, elements);
        List<Certificate> foundedCertificates = new ArrayList<>();
        fillListIfEmpty(foundByPartOfName, foundByTagNames, sortedByParam);
        foundByTagNames.stream().filter(foundByPartOfName::contains);
        foundedCertificates.addAll(foundByTagNames);
        if (!sortedByParam.isEmpty()) {
            sortedByParam.retainAll(foundedCertificates);
            foundedCertificates = sortedByParam;
        }
        return foundedCertificates;
    }

    private List<Certificate> getListSortedByParameter(MultiValueMap<String, String> allRequestParams, int page, int elements) {
        String sortType;
        List<Certificate> sortedList = new ArrayList<>();
        if (allRequestParams.containsKey(SORT_BY_NAME)) {
            sortType = getAllParameters(SORT_BY_NAME, allRequestParams).get(0);
            sortedList = dao.getEntitiesSortedByParameter(sortType, SORT_BY_NAME, page, elements);
        } else if (allRequestParams.containsKey(SORT_BY_DATE)) {
            sortType = getAllParameters(SORT_BY_DATE, allRequestParams).get(0);
            sortedList = dao.getEntitiesSortedByParameter(sortType, SORT_BY_DATE, page, elements);
        }
        return sortedList;
    }
}
