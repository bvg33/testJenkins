package com.epam.ems.logic.creator;

import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import com.epam.ems.logic.handler.DateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Component
public class CertificateCreator implements Creator<Certificate> {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String TAG = "tag";
    private static final String PRICE = "price";
    private static final String DURATION = "duration";

    @Autowired
    private DateHandler dateHandler;

    @Override
    public Certificate createObject(MultiValueMap<String, String> allRequestParams) {
        int id = getIntField(ID, allRequestParams);
        String name = getStringField(NAME, allRequestParams);
        String description = getStringField(DESCRIPTION, allRequestParams);
        int price = getIntField(PRICE, allRequestParams);
        int duration = getIntField(DURATION, allRequestParams);
        List<Tag> tags = getTags(allRequestParams);
        String currentDate = dateHandler.getCurrentDate();
        if (id != 0) {
            return new Certificate(id, name, description, price, duration, null, currentDate, tags);
        }
        return new Certificate(name, description, price, duration, currentDate, currentDate, tags);
    }

    private int getIntField(String fieldName, MultiValueMap<String, String> allRequestParams) {
        if (allRequestParams.containsKey(fieldName)) {
            String stringField = allRequestParams.get(fieldName).get(0);
            return Integer.parseInt(stringField);
        }
        return 0;
    }

    private String getStringField(String fieldName, MultiValueMap<String, String> allRequestParams) {
        if (allRequestParams.containsKey(fieldName)) {
            return allRequestParams.get(fieldName).get(0);
        }
        return null;
    }

    private List<Tag> getTags(MultiValueMap<String, String> allRequestParams) {
        if (allRequestParams.containsKey(TAG)) {
            List<Tag> tags = new ArrayList<>();
            List<String> tagsNames = allRequestParams.get(TAG);
            tagsNames.stream().forEach(tagName -> tags.add(new Tag(tagName)));
            return tags;
        }
        return new ArrayList<>();
    }
}
