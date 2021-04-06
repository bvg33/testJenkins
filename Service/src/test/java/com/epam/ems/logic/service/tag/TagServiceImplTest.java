package com.epam.ems.logic.service.tag;

import com.epam.ems.dao.tagdao.TagDaoImpl;
import com.epam.ems.dto.Tag;
import com.epam.ems.exceptions.DaoException;
import com.epam.ems.logic.creator.Creator;
import com.epam.ems.logic.creator.TagCreator;
import com.epam.ems.service.tag.TagServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
public class TagServiceImplTest {
    @Mock
    private Creator<Tag> creator = Mockito.mock(TagCreator.class);
    @Mock
    private TagDaoImpl dao = Mockito.mock(TagDaoImpl.class);

    @InjectMocks
    private TagServiceImpl service;

    private List<Tag> tags = Arrays.asList(new Tag(1, "chill"), new Tag(2, "work"), new Tag(3, "spa"));

    @Test
    public void testGetAll() {
        when(dao.getAll()).thenReturn(tags);
        List<Tag> actual = service.getAll();
        assertEquals(tags, actual);
    }

    @Test
    public void testGetById() throws Exception {
        when(dao.getById(1)).thenReturn(tags.get(0));
        Tag actual = service.getById(1);
        Tag expected = new Tag(1, "chill");
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveById() throws DaoException {
        List<Tag> actual = new ArrayList<>(tags);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return actual.remove(0);
            }
        }).when(dao).removeById(1);
        service.deleteById(1);
        List<Tag> expected = Arrays.asList(new Tag(2, "work"), new Tag(3, "spa"));
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
        List<Tag> actual = new ArrayList<>(Arrays.asList(new Tag(1, "chill"),
                new Tag(2, "work")));
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return actual.add(new Tag(3, "spa"));
            }
        }).when(dao).save(any(Tag.class));
        when(creator.createObject(anyObject())).thenReturn(new Tag("spa"));
        MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();
        fields.add("name", "spa");
        service.update(fields);
        assertEquals(tags, actual);
    }

    @Test
    public void testFilter_whenFilterByPartOfName() {
        when(dao.getByNamePart(anyString())).thenReturn(Arrays.asList(new Tag(2, "work")));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("getByNamePart", "o");
        List<Tag> actual = service.doFilter(params);
        List<Tag> expected = Arrays.asList(new Tag(2, "work"));
        assertEquals(expected, actual);
    }

    @Test
    public void testFilter_whenFilterByTagName() {
        when(dao.getByTagName(anyString())).thenReturn(Arrays.asList(new Tag(2, "work")));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("getByTagName", "work");
        List<Tag> actual = service.doFilter(params);
        List<Tag> expected = Arrays.asList(new Tag(2, "work"));
        assertEquals(expected, actual);
    }

    @Test
    public void testFilter_whenFilterSortByName() {
        when(dao.getEntitiesSortedByParameter(anyString(), anyString())).thenReturn(new ArrayList<>(tags));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sortByName", "asc");
        List<Tag> actual = service.doFilter(params);
        assertEquals(tags, actual);
    }
}
