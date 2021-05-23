package com.epam.ems.controllers;

import com.epam.ems.dto.AppUser;
import com.epam.ems.dto.Role;
import com.epam.ems.dto.UserCredentials;
import com.epam.ems.exceptions.InvalidCredentialsException;
import com.epam.ems.hateoas.AuthUserHateoas;
import com.epam.ems.jwt.JWTProvider;
import com.epam.ems.request.RequestProcessor;
import com.epam.ems.service.user.UserService;
import com.epam.ems.service.userdetail.SecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

import static java.util.Objects.isNull;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityUserDetailService userDetailService;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private AuthUserHateoas hateoas;
    private static final String INVALID_CREDENTIALS = "Invalid Credentials";

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserCredentials credentials) throws Exception {
        AppUser user = new AppUser(encoder.encode(credentials.getPassword()), Role.USER, credentials.getNickname(),
                0, new ArrayList<>(), 0);
        userService.saveUser(user);
        RepresentationModel model = new RepresentationModel();
        hateoas.createHateoas(model);
        return ResponseEntity.ok(model);
    }

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody UserCredentials credentials) throws Exception {
        UserDetails userDetails = userDetailService.loadUserByUsername(credentials.getNickname());
        if (!isNull(userDetails) && encoder.matches(credentials.getPassword(), userDetails.getPassword())) {
            String token = jwtProvider.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(token);
        } else {
            throw new InvalidCredentialsException(INVALID_CREDENTIALS);
        }
    }
}
