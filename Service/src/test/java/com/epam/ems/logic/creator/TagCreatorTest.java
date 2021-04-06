package com.epam.ems.logic.creator;

import com.epam.ems.dto.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagCreatorTest {

    private final TagCreator creator  =new TagCreator();

    @Test
    public void testCreateObject() {
        MultiValueMap<String, String> fields = new LinkedMultiValueMap<>();
        fields.add("name", "asd");
        Tag actual = creator.createObject(fields);
        Tag expected = new Tag("asd");
        assertEquals(expected, actual);
    }


}
