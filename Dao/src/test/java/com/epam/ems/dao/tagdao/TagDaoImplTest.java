package com.epam.ems.dao.tagdao;


import com.epam.ems.dao.config.HSQLDBConfig;
import com.epam.ems.dao.CRDDao;
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
@ContextConfiguration(classes = {TagDaoImpl.class, HSQLDBConfig.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class TagDaoImplTest {

    @Autowired
    private CRDDao<Tag> dao;

    @Test
    public void testGetAll() {
        List<Tag> actual = dao.getAll(1, 5);
        List<Tag> expected = Arrays.asList(new Tag("tag1"), new Tag("tag2"), new Tag("tag3"));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() {
        Tag actual = dao.getById(1);
        Tag expected = new Tag("tag1");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetByTagName() {
        List<Tag> actual = dao.getByTagName("tag1", 1, 5);
        List<Tag> expected = Arrays.asList(new Tag("tag1"));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetByNamePart() {
        List<Tag> actual = dao.getByNamePart("a", 1, 5);
        List<Tag> expected = Arrays.asList(new Tag("tag1"), new Tag("tag2"), new Tag("tag3"));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEntitiesSortedByParameter() {
        List<Tag> actual = dao.getEntitiesSortedByParameter("desc", "sortByName", 1, 5);
        List<Tag> expected = Arrays.asList(new Tag("tag3"), new Tag("tag2"), new Tag("tag1"));
        assertEquals(expected, actual);
    }
}
