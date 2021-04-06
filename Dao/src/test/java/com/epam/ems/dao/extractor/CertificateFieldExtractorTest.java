package com.epam.ems.dao.extractor;

import com.epam.ems.dto.Certificate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificateFieldExtractorTest {

    private final CertificateFieldExtractor extractor = new CertificateFieldExtractor();

    @Test
    public void testExtract() {
        Certificate certificate = new Certificate(1, "name1", "desc1", 4, 5, new String(), new String(), new ArrayList<>());
        Map<String, String> actual = extractor.extract(certificate);
        Map<String, String> expected = new HashMap<>();
        expected.put("id", "1");
        expected.put("name", "name1");
        expected.put("description", "desc1");
        expected.put("price", "4");
        expected.put("duration", "5");
        expected.put("create_date", new String());
        expected.put("last_update_date", new String());
        assertEquals(expected, actual);
    }
}
