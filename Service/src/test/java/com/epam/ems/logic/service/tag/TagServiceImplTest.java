package com.epam.ems.logic.service.tag;

import com.epam.ems.dao.tagdao.TagDaoImpl;
import com.epam.ems.dto.Tag;
import com.epam.ems.logic.serviceconfig.HSQLDBConfig;
import com.epam.ems.service.tag.TagServiceImpl;
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
@ContextConfiguration(classes = {TagServiceImpl.class, TagDaoImpl.class, HSQLDBConfig.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class TagServiceImplTest {
    @Autowired
    private TagServiceImpl service;

    @Test
    public void testGetAll() {
        List<Tag> actual = service.getAll(1, 5);
        List<Tag> expected = Arrays.asList(new Tag("tag1"), new Tag("tag2"), new Tag("tag3"));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() {
        Tag actual = service.getById(1);
        Tag expected = new Tag(1, "tag1");
        assertEquals(expected, actual);
    }

    @Test
    public void testFilter_whenFilterByPartOfName() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("getByNamePart", "1");
        List<Tag> actual = service.doFilter(params, 1, 4);
        List<Tag> expected = Arrays.asList(new Tag(1, "tag1"));
        assertEquals(expected, actual);
    }

    @Test
    public void testFilter_whenFilterByTagName() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("getByTagName", "tag1");
        List<Tag> actual = service.doFilter(params, 1, 4);
        List<Tag> expected = Arrays.asList(new Tag(1, "tag1"));
        assertEquals(expected, actual);
    }

    @Test
    public void testFilter_whenFilterSortByName() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sortByName", "asc");
        List<Tag> actual = service.doFilter(params, 1, 4);
        List<Tag> expected = Arrays.asList(new Tag("tag1"), new Tag("tag2"), new Tag("tag3"));
        assertEquals(expected, actual);
    }
}
