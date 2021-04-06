package com.epam.ems.dao.creator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateCertificateQueryCreatorTest {

    private final UpdateCertificateQueryCreator creator=new UpdateCertificateQueryCreator();

    @Test
    public void testCreateQuery(){
        Map<String,String> fields = new HashMap<>();
        fields.put("id","1");
        fields.put("name","name");
        String actual=creator.createQuery(fields);
        String expected="update epam.gift_certificate set name='name' where id=1";
        assertEquals(expected,actual);
    }
}
