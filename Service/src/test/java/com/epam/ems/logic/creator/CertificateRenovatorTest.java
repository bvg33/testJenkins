package com.epam.ems.logic.creator;

import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.Tag;
import com.epam.ems.logic.handler.DateHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
public class CertificateRenovatorTest {

    @Mock
    private DateHandler dateHandler = Mockito.mock(DateHandler.class);

    @InjectMocks
    private CertificateRenovator renovator;

    @Test
    public void testUpdateObject() {
        Certificate newCertificate = new Certificate("name", null, 0,
                5, "2021-11-10 00:00:00", "2021-11-10 00:00:00",
                new ArrayList<>(Arrays.asList(new Tag("tag"))));
        Certificate oldCertificate = new Certificate(1, "name1", "desc", 5, 3,
                "2021-11-10 00:00:00", "2021-11-10 00:00:00",
                new ArrayList<>(Arrays.asList(new Tag("tag"))));
        Certificate expected = new Certificate(1, "name", "desc", 5, 5,
                "2021-11-10 00:00:00", "2021-11-10 00:00:00",
                new ArrayList<>(Arrays.asList(new Tag("tag"))));
        when(dateHandler.getCurrentDate()).thenReturn("2021-11-10 00:00:00");

        Certificate actual = renovator.updateObject(newCertificate, oldCertificate);
        assertEquals(expected, actual);
    }
}
