package com.epam.ems.dao.certificatedao;

import com.epam.ems.config.HSQLDBConfig;
import com.epam.ems.dao.mapper.CertificateRowMapper;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import com.epam.ems.exceptions.DaoException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CertificateDaoImplTest {
    private HSQLDBConfig config = new HSQLDBConfig();
    private CertificateDaoImpl dao = new CertificateDaoImpl(config.dataSource(), new CertificateRowMapper());

    @Test
    public void testGetAll() {
        List<Certificate> actual = dao.getAll();
        List<Certificate> expected = Arrays.asList(new Certificate("sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate("name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))),
                new Certificate("sdfkl", "1", 5, 4,
                        "2021-04-05 15:03:00.000000", "2021-04-05 15:03:00.000000", Arrays.asList(new Tag("tag3"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws DaoException {
        Certificate actual = dao.getById(1);
        Certificate expected = new Certificate("sad", "dsf", 4, 5,
                "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2")));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetByTagName() {
        List<Certificate> actual = dao.getByTagName("tag2");
        List<Certificate> expected = Arrays.asList(new Certificate("sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate("name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetByNamePart() {
        List<Certificate> actual = dao.getByNamePart("a");
        List<Certificate> expected = Arrays.asList(new Certificate("sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate("name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEntitiesSortedByParam_sortByName() {
        List<Certificate> actual = dao.getEntitiesSortedByParameter("asc", "sortByName");
        List<Certificate> expected = Arrays.asList(new Certificate("name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))),
                new Certificate("sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))),
                new Certificate("sdfkl", "1", 5, 4,
                        "2021-04-05 15:03:00.000000", "2021-04-05 15:03:00.000000", Arrays.asList(new Tag("tag3"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEntitiesSortedByParam_sortByDate(){
        List<Certificate> actual = dao.getEntitiesSortedByParameter("asc", "sortByDate");
        List<Certificate> expected = Arrays.asList(new Certificate("name1", "description", 5, 5,
                        "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", Arrays.asList(new Tag("tag2"))),
                new Certificate("sdfkl", "1", 5, 4,
                        "2021-04-05 15:03:00.000000", "2021-04-05 15:03:00.000000", Arrays.asList(new Tag("tag3"))),
                new Certificate("sad", "dsf", 4, 5,
                        "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000", Arrays.asList(new Tag("tag1"), new Tag("tag2"))));
        assertEquals(expected, actual);
    }
}
