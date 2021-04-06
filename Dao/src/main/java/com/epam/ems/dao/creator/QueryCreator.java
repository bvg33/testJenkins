package com.epam.ems.dao.creator;

import java.util.Map;

public interface QueryCreator {
    String createQuery(Map<String, String> fields);
}
