package com.epam.ems.validator;

import com.epam.ems.dto.AppUser;
import com.epam.ems.dto.Role;
import com.epam.ems.request.RequestProcessor;
import com.epam.ems.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RootValidator {
    @Autowired
    private UserService service;
    @Autowired
    private RequestProcessor processor;

    public boolean hasAccess(int userId, HttpServletRequest request) {
        String token = processor.getTokenFromRequest(request);
        String login = processor.getLoginFromToken(token);
        AppUser user = service.getUserByUserName(login);
        return userId == user.getId() || user.getRole() == Role.ADMIN;
    }
}
