package com.epam.ems.logic.creator;

import org.springframework.util.MultiValueMap;

public interface Creator<T> {
    T createObject(MultiValueMap<String, String> allRequestParams);
}
