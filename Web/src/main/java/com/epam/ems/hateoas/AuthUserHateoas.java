package com.epam.ems.hateoas;

import com.epam.ems.controllers.AuthController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AuthUserHateoas implements Hateoas {
    @Override
    public void createHateoas(RepresentationModel entity) throws Exception {
        entity.add(linkTo(methodOn(AuthController.class).auth(null)).withSelfRel());
        entity.add(linkTo(methodOn(AuthController.class).registerUser(null)).withSelfRel());
    }
}
