package com.epam.ems.dao.extractor;

import java.util.Map;

public interface FieldExtractor<T> {
    Map<String, String> extract(T item);
}
