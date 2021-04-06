package com.epam.ems.controllers;

import com.epam.ems.exceptions.DaoException;
import com.epam.ems.service.Service;
import com.epam.ems.dto.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagsController {
    @Autowired
    private Service<Tag> service;

    @GetMapping
    public List<Tag> AllTags() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Tag TagById(@PathVariable int id) throws Exception {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable int id) throws DaoException {
        service.deleteById(id);
        return "Deleted successfully";
    }

    @PostMapping("/new")
    public String createTag(@RequestParam MultiValueMap<String, String> allRequestParams) {
        service.update(allRequestParams);
        return "Created successfully";
    }

    @GetMapping("/filter")
    public List<Tag> sortByParam(@RequestParam MultiValueMap<String, String> allRequestParams) {
        return service.doFilter(allRequestParams);
    }
}
