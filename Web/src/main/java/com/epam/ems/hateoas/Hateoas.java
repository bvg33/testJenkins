package com.epam.ems.hateoas;

import org.springframework.hateoas.RepresentationModel;

public interface Hateoas {
    void createHateoas(RepresentationModel representationModel) throws Exception;
}
