package com.epam.ems.logic.service.certificate;

import com.epam.ems.dao.certificatedao.CertificateDaoImpl;
import com.epam.ems.dao.tagdao.TagDaoImpl;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import com.epam.ems.logic.creator.CertificateRenovator;
import com.epam.ems.logic.handler.DateHandler;
import com.epam.ems.logic.serviceconfig.HSQLDBConfig;
import com.epam.ems.service.certificate.CertificateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CertificateService.class, CertificateDaoImpl.class,
        TagDaoImpl.class, HSQLDBConfig.class, CertificateRenovator.class, DateHandler.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class CertificateServiceTest {

    @Autowired
    private CertificateService service;

    @Test
    public void testGetAll() {
        List<Certificate> actual = service.getAll(1, 5);
        List<Certificate> expected = Arrays.asList(new Certificate(1, "sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate(2, "name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))),
                new Certificate(3, "sdfkl", "1", 5, 4,
                        "2021-04-05 15:03:00.000000", "2021-04-05 15:03:00.000000", Arrays.asList(new Tag("tag3"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() {
        Certificate actual = service.getById(1);
        Certificate expected = new Certificate(1, "sad", "dsf", 4, 5,
                "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2")));
        assertEquals(expected, actual);
    }

    @Test
    public void testFilter_whenFilterByTagName() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("getByTagName", "tag2");
        List<Certificate> actual = service.doFilter(params, 1, 5);
        List<Certificate> expected = Arrays.asList(new Certificate(1, "sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate(2, "name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testFilter_whenFilterByPartOfName() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("getByNamePart", "a");
        List<Certificate> actual = service.doFilter(params, 1, 5);
        List<Certificate> expected = Arrays.asList(new Certificate(1, "sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate(2, "name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testFilter_whenFilterByMultiParams() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("getByNamePart", "a");
        params.add("getByTagName", "tag1");
        List<Certificate> actual = service.doFilter(params, 1, 5);
        List<Certificate> expected = Arrays.asList(new Certificate(1, "sad", "dsf", 4, 5,
                "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000",
                Arrays.asList(new Tag("tag1"), new Tag("tag2"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEntitiesSortedByParam_sortByName() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sortByName", "asc");
        List<Certificate> actual = service.doFilter(params, 1, 5);
        List<Certificate> expected = Arrays.asList(new Certificate(2, "name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))),
                new Certificate(1, "sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate(3, "sdfkl", "1", 5, 4,
                        "2021-04-05 15:03:00.000000", "2021-04-05 15:03:00.000000", Arrays.asList(new Tag("tag3"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEntitiesSortedByParam_sortByDate() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sortByDate", "asc");
        List<Certificate> actual = service.doFilter(params, 1, 5);
        List<Certificate> expected = Arrays.asList(new Certificate(2, "name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))),
                new Certificate(3, "sdfkl", "1", 5, 4,
                        "2021-04-05 15:03:00.000000", "2021-04-05 15:03:00.000000", Arrays.asList(new Tag("tag3"))),
                new Certificate(1, "sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))));
        assertEquals(expected, actual);
    }

}
