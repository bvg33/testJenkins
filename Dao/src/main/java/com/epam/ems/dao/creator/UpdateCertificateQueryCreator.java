package com.epam.ems.dao.creator;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.StringJoiner;

@Component
public class UpdateCertificateQueryCreator implements QueryCreator {
    public String createQuery(Map<String, String> fields) {
        StringJoiner joiner = new StringJoiner(",");
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            if (!entry.getKey().equals("id") && entry.getValue() != null) {
                joiner.add(entry.getKey() + "=" + '\'' + entry.getValue() + '\'');
            }
        }
        String query = "update epam.gift_certificate set " + joiner + " where id=" + fields.get("id");
        return query;
    }
}
