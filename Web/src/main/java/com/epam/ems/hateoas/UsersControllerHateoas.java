package com.epam.ems.hateoas;

import com.epam.ems.controllers.UsersController;
import com.epam.ems.exceptions.NoRootsException;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsersControllerHateoas implements Hateoas {

    @Override
    public void createHateoas(RepresentationModel entity) throws NoRootsException {
        entity.add(linkTo(methodOn(UsersController.class).getAllUsers(1, 5)).withSelfRel());
        entity.add(linkTo(methodOn(UsersController.class).getTheMostWidelyUsedTag()).withSelfRel());
        entity.add(linkTo(methodOn(UsersController.class).getUserCertificates(1, 1, 5, null)).withSelfRel());
        entity.add(linkTo(methodOn(UsersController.class).getUserOrderInfo(1, 1, 5)).withSelfRel());
    }
}
