package com.epam.ems.logic.service.user;

import com.epam.ems.dao.certificatedao.CertificateDaoImpl;
import com.epam.ems.dao.orderdao.OrderDaoImpl;
import com.epam.ems.dao.tagdao.TagDaoImpl;
import com.epam.ems.dao.userdao.UserDaoImpl;
import com.epam.ems.dto.*;
import com.epam.ems.logic.creator.CertificateRenovator;
import com.epam.ems.logic.handler.DateHandler;
import com.epam.ems.logic.serviceconfig.HSQLDBConfig;
import com.epam.ems.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserService.class, CertificateDaoImpl.class, UserDaoImpl.class,
        OrderDaoImpl.class, TagDaoImpl.class, HSQLDBConfig.class, CertificateRenovator.class, DateHandler.class}, loader = AnnotationConfigContextLoader.class)
@SpringBootTest
public class AppUserServiceTest {

    private final List<AppUser> users = asList(new AppUser(1, "password", Role.USER, "dima", 50,
                    asList(new Certificate(1, "sad", "dsf", 4, 5,
                            "2021-11-10 00:00:00.000000", "2021-11-10 00:00:00.000000",
                            asList(new Tag("tag1"), new Tag("tag2")))), 15),
            new AppUser(2, "password", Role.USER, "vasya", 30, asList(new Certificate(2, "name1", "description", 5, 5,
                    "2021-04-03 21:07:00.000000", "2021-04-04 20:46:00.000000", asList(new Tag("tag2")))), 5));

    @Autowired
    private UserService service;

    @Test
    public void testGetAllUsers() {
        List<AppUser> actual = service.getAllUsers(1, 2);
        List<AppUser> expected = new ArrayList<>(users);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetUserCertificates() {
        List<Certificate> actual = service.getUserCertificates(1, 1, 4);
        List<Certificate> expected = users.get(0).getCertificates();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetUserOrdersInfo() {
        List<UserOrderInfo> actual = service.getUserOrdersInfo(1, 1, 5);
        List<UserOrderInfo> expected = asList(new UserOrderInfo(1, 1, 5, "2021-11-10 00:00:00.000000"));
        assertEquals(expected, actual);
    }
}
