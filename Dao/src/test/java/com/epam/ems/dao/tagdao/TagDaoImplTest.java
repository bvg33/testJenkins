package com.epam.ems.dao.tagdao;

import com.epam.ems.config.HSQLDBConfig;
import com.epam.ems.config.MySqlDataBaseConfig;
import com.epam.ems.dao.mapper.TagRowMapper;
import com.epam.ems.dto.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagDaoImplTest {

    private HSQLDBConfig config=new HSQLDBConfig();
    private DataSourceInitializer initializer=new DataSourceInitializer();
    private TagDaoImpl dao=new TagDaoImpl(config.dataSource(),new TagRowMapper());

    @Test
    public void testGetAll() {
        List<Tag> actual= dao.getAll();
        List<Tag> expected= Arrays.asList(new Tag("tag1"),new Tag("tag2"),new Tag("tag3"));
        assertEquals(expected,actual);
    }

    @Test
    public void testGetById() throws Exception {
        Tag actual= dao.getById(1);
        Tag expected= new Tag("tag1");
        assertEquals(expected,actual);
    }

    @Test
    public void testGetByTagName() {
        List<Tag> actual= dao.getByTagName("tag1");
        List<Tag> expected= Arrays.asList(new Tag("tag1"));
        assertEquals(expected,actual);
    }

    @Test
    public void testGetByNamePart() {
        List<Tag> actual= dao.getByNamePart("a");
        List<Tag> expected= Arrays.asList(new Tag("tag1"),new Tag("tag2"),new Tag("tag3"));
        assertEquals(expected,actual);
    }

    @Test
    public void testGetEntitiesSortedByParameter() {
        List<Tag> actual= dao.getEntitiesSortedByParameter("desc","sortByName");
        List<Tag> expected= Arrays.asList(new Tag("tag3"),new Tag("tag2"),new Tag("tag1"));
        assertEquals(expected,actual);
    }
    //todo ask about insert method test
}
