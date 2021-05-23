package com.epam.ems.hateoas;

import com.epam.ems.controllers.CertificateController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.epam.ems.hateoas.HATEOASConstant.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CertificatesControllerHateoas implements Hateoas {

    public static final String UPDATE = "update";
    public static final String ID = "id";

    @Override
    public void createHateoas(RepresentationModel entity) {
        entity.add(linkTo(methodOn(CertificateController.class).getCertificateById(1)).withSelfRel());
        entity.add(linkTo(methodOn(CertificateController.class).getAllCertificates(1, 5)).withSelfRel());
        entity.add(linkTo(CertificateController.class).slash(NEW).withSelfRel());
        entity.add(linkTo(CertificateController.class).slash(1).withSelfRel());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(GET_BY_TAG_NAME, TAG);
        entity.add(linkTo(methodOn(CertificateController.class).filter(1, 1, map)).withSelfRel());
        entity.add(linkTo(CertificateController.class).slash(UPDATE).slash(ID).withSelfRel());
    }
}
