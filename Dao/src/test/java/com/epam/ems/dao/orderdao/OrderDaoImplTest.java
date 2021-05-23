package com.epam.ems.dao.orderdao;

import com.epam.ems.dao.config.HSQLDBConfig;
import com.epam.ems.dto.UserOrderInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {OrderDaoImpl.class, HSQLDBConfig.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class OrderDaoImplTest {

    @Autowired
    private OrderDaoImpl dao;

    @Test
    public void testGetUserCertificateInfo() {
        List<UserOrderInfo> actual = dao.getUserCertificatesInfo(1, 1, 5);
        List<UserOrderInfo> expected = asList(new UserOrderInfo(1, 1, 1, 5, "2021-11-10 00:00:00.000000"));
        assertEquals(expected, actual);
    }
}
