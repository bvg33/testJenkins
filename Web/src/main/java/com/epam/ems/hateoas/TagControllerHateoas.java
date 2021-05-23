package com.epam.ems.hateoas;

import com.epam.ems.controllers.TagsController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.epam.ems.hateoas.HATEOASConstant.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TagControllerHateoas implements Hateoas {

    @Override
    public void createHateoas(RepresentationModel entity) {
        entity.add(linkTo(methodOn(TagsController.class).TagById(1)).withSelfRel());
        entity.add(linkTo(methodOn(TagsController.class).AllTags(1, 5)).withSelfRel());
        entity.add(linkTo(TagsController.class).slash(NEW).withSelfRel());
        entity.add(linkTo(TagsController.class).slash(1).withSelfRel());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(GET_BY_TAG_NAME, TAG);
        entity.add(linkTo(methodOn(TagsController.class).sortByParam(1, 1, map)).withSelfRel());
    }
}
