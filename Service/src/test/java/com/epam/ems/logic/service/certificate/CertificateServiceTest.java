package com.epam.ems.logic.service.certificate;

import com.epam.ems.dao.Dao;
import com.epam.ems.dao.certificatedao.CertificateDaoImpl;
import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import com.epam.ems.exceptions.DaoException;
import com.epam.ems.logic.creator.CertificateCreator;
import com.epam.ems.logic.creator.Creator;
import com.epam.ems.service.certificate.CertificateService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
public class CertificateServiceTest {

    @Mock
    private Dao<Certificate> dao = Mockito.mock(CertificateDaoImpl.class);

    @Mock
    private Creator<Certificate> creator = Mockito.mock(CertificateCreator.class);

    @InjectMocks
    private CertificateService service;

    private List<Certificate> certificates = Arrays.asList(new Certificate("name1", "desc1", 5, 6, new String(), new String(), new ArrayList<>()),
            new Certificate("name2", "desc2", 3, 4, new String(), new String(), new ArrayList<>()),
            new Certificate("name3", "desc3", 1, 2, new String(), new String(), new ArrayList<>()));

    @Test
    public void testGetAll() {
        when(dao.getAll()).thenReturn(certificates);
        List<Certificate> actual = service.getAll();
        List<Certificate> expected = Arrays.asList(new Certificate("name1", "desc1", 5, 6, new String(), new String(), new ArrayList<>()),
                new Certificate("name2", "desc2", 3, 4, new String(), new String(), new ArrayList<>()),
                new Certificate("name3", "desc3", 1, 2, new String(), new String(), new ArrayList<>()));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws Exception {
        when(dao.getById(1)).thenReturn(certificates.get(0));
        Certificate actual = service.getById(1);
        Certificate expected = new Certificate("name1", "desc1", 5, 6, new String(), new String(), new ArrayList<>());
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveById() throws DaoException {
        List<Certificate> actual = new ArrayList<>(certificates);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return actual.remove(0);
            }
        }).when(dao).removeById(1);
        service.deleteById(1);
        List<Certificate> expected = Arrays.asList(new Certificate("name2", "desc2", 3, 4, new String(), new String(), new ArrayList<>()),
                new Certificate("name3", "desc3", 1, 2, new String(), new String(), new ArrayList<>()));
        assertEquals(expected, actual);
    }

    @Test
    public void testFilter_whenFilterByPartOfName() {
        when(dao.getByNamePart(anyString())).thenReturn(Arrays.asList(new Certificate("name1", "desc1",
                5, 6, new String(), new String(), Arrays.asList(new Tag("work")))));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("getByNamePart", "a");
        List<Certificate> actual = service.doFilter(params);
        List<Certificate> expected = Arrays.asList(new Certificate("name1", "desc1",
                5, 6, new String(), new String(), Arrays.asList(new Tag("work"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testFilter_whenFilterByTagName() {
        when(dao.getByTagName(anyString())).thenReturn(Arrays.asList(new Certificate("name1", "desc1",
                5, 6, new String(), new String(), Arrays.asList(new Tag("work")))));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("getByTagName", "work");
        List<Certificate> actual = service.doFilter(params);
        List<Certificate> expected = Arrays.asList(new Certificate("name1", "desc1",
                5, 6, new String(), new String(), Arrays.asList(new Tag("work"))));
        assertEquals(expected, actual);
    }

    @Test
    public void testFilter_whenFilterSortByName() {
        when(dao.getEntitiesSortedByParameter(anyString(), anyString())).thenReturn(certificates);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sortByName", "asc");
        List<Certificate> actual = service.doFilter(params);
        assertEquals(certificates, actual);
    }

}
