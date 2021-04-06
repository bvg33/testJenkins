package com.epam.ems.controllers;

import com.epam.ems.dto.Tag;
import com.epam.ems.exceptions.DaoException;
import com.epam.ems.service.Service;
import com.epam.ems.dto.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {
    @Autowired
    private Service<Certificate> service;

    @GetMapping
    public List<Certificate> getAllCertificates() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Certificate getCertificateById(@PathVariable int id) throws Exception {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCertificate(@PathVariable int id) throws DaoException {
        service.deleteById(id);
        return "Certificate was successfully deleted";
    }

    @PostMapping("/new")
    public String addCertificate(@RequestParam MultiValueMap<String, String> allRequestParams) {
        service.update(allRequestParams);
        return "Certificate was successfully added";
    }

    @PatchMapping("/update/{id}")
    public String updateCertificate(@PathVariable int id, @RequestParam MultiValueMap<String, String> allRequestParams) {
        allRequestParams.add("id", String.valueOf(id));
        service.update(allRequestParams);
        return "Certificate was successfully updated";
    }

    @GetMapping("/filter")
    public List<Certificate> filter(@RequestParam MultiValueMap<String, String> allRequestParams) {
        return service.doFilter(allRequestParams);
    }
}
