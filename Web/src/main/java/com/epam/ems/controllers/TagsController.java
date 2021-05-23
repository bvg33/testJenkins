package com.epam.ems.controllers;

import com.epam.ems.dto.Role;
import com.epam.ems.dto.Tag;
import com.epam.ems.dto.lists.TagList;
import com.epam.ems.hateoas.TagControllerHateoas;
import com.epam.ems.service.CRDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

import static org.springframework.http.HttpStatus.*;

@Validated
@RestController
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagControllerHateoas hateoas;
    @Autowired
    private CRDService<Tag> service;

    @GetMapping
    public ResponseEntity AllTags(@Min(1) @RequestParam int page, @Min(1) @RequestParam int elements) {
        TagList list = new TagList(service.getAll(page, elements));
        hateoas.createHateoas(list);
        return ResponseEntity.status(OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> TagById(@Min(1) @PathVariable int id) {
        Tag tag = service.getById(id);
        hateoas.createHateoas(tag);
        return ResponseEntity.status(OK).body(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTag(@Min(1) @PathVariable int id) {
        service.deleteById(id);
        RepresentationModel model = new RepresentationModel();
        hateoas.createHateoas(model);
        return ResponseEntity.status(NO_CONTENT).body(model);
    }

    @PostMapping("/new")
    public ResponseEntity createTag(@RequestBody Tag tag) {
        service.insertIntoDB(tag);
        RepresentationModel model = new RepresentationModel();
        hateoas.createHateoas(model);
        return ResponseEntity.status(CREATED).body(model);
    }

    @GetMapping("/filter")
    public ResponseEntity<TagList> sortByParam(@Min(1) @RequestParam int page, @Min(1) @RequestParam int elements,
                                               @RequestParam MultiValueMap<String, String> allRequestParams) {
        TagList list = new TagList(service.doFilter(allRequestParams, page, elements));
        hateoas.createHateoas(list);
        return ResponseEntity.status(OK).body(list);
    }
}
