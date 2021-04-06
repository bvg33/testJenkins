package com.epam.ems.logic.creator;

import com.epam.ems.dto.Tag;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class TagCreator implements Creator<Tag> {
    private static final String NAME = "name";

    @Override
    public Tag createObject(MultiValueMap<String, String> allRequestParams) {
        String name = allRequestParams.get(NAME).get(0);
        return new Tag(name);
    }
}
