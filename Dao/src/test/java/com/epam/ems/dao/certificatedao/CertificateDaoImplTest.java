package com.epam.ems.dao.certificatedao;


import com.epam.ems.dao.CRUDDao;
import com.epam.ems.dao.config.HSQLDBConfig;
import com.epam.ems.dao.tagdao.TagDaoImpl;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CertificateDaoImpl.class, TagDaoImpl.class, HSQLDBConfig.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class CertificateDaoImplTest {

    @Autowired
    private CRUDDao<Certificate> dao;

    @Test
    public void testGetAll() {
        List<Certificate> actual = dao.getAll(1, 5);
        List<Certificate> expected = Arrays.asList(new Certificate("sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate("name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))),
                new Certificate("sdfkl", "1", 5, 4,
                        "2021-04-05 15:03:00.000000", "2021-04-05 15:03:00.000000", Arrays.asList(new Tag("tag3"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() {
        Certificate actual = dao.getById(1);
        Certificate expected = new Certificate("sad", "dsf", 4, 5,
                "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2")));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetByTagName() {
        List<Certificate> actual = dao.getByTagName("tag2", 1, 5);
        List<Certificate> expected = Arrays.asList(new Certificate("sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate("name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetByNamePart() {
        List<Certificate> actual = dao.getByNamePart("a", 1, 5);
        List<Certificate> expected = Arrays.asList(new Certificate("sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate("name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEntitiesSortedByParam_sortByName() {
        List<Certificate> actual = dao.getEntitiesSortedByParameter("asc", "sortByName", 1, 5);
        List<Certificate> expected = Arrays.asList(new Certificate("name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))),
                new Certificate("sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate("sdfkl", "1", 5, 4,
                        "2021-04-05 15:03:00.000000", "2021-04-05 15:03:00.000000", Arrays.asList(new Tag("tag3"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEntitiesSortedByParam_sortByDate() {
        List<Certificate> actual = dao.getEntitiesSortedByParameter("asc", "sortByDate", 1, 5);
        List<Certificate> expected = Arrays.asList(new Certificate("name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))),
                new Certificate("sdfkl", "1", 5, 4,
                        "2021-04-05 15:03:00.000000", "2021-04-05 15:03:00.000000", Arrays.asList(new Tag("tag3"))),
                new Certificate("sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))));
        assertEquals(expected, actual);
    }
}
