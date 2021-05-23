package com.epam.ems.controllers;

import com.epam.ems.dto.Certificate;
import com.epam.ems.dto.lists.CertificateList;
import com.epam.ems.hateoas.CertificatesControllerHateoas;
import com.epam.ems.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CRUDService<Certificate> service;

    @Autowired
    private CertificatesControllerHateoas hateoas;

    @GetMapping
    public ResponseEntity<CertificateList> getAllCertificates(@Min(1) @RequestParam int page,
                                                              @Min(1) @RequestParam int elements) {
        CertificateList list = new CertificateList(service.getAll(page, elements));
        hateoas.createHateoas(list);
        return ResponseEntity.status(OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificateById(@Min(1) @PathVariable int id) {
        Certificate certificate = service.getById(id);
        hateoas.createHateoas(certificate);
        return ResponseEntity.status(OK).body(certificate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCertificate(@Min(1) @PathVariable int id) {
        service.deleteById(id);
        RepresentationModel model = new RepresentationModel();
        hateoas.createHateoas(model);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(model);
    }

    @PostMapping("/new")
    public ResponseEntity addCertificate(@RequestBody Certificate certificate) {
        service.insertIntoDB(certificate);
        RepresentationModel model = new RepresentationModel();
        hateoas.createHateoas(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity updateCertificate(@Min(1) @PathVariable int id, @RequestBody Certificate certificate) {
        service.update(certificate, id);
        RepresentationModel model = new RepresentationModel();
        hateoas.createHateoas(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @GetMapping("/filter")
    public ResponseEntity filter(@Min(1) @RequestParam int page, @Min(1) @RequestParam int elements,
                                 @RequestParam MultiValueMap<String, String> allRequestParams) {
        CertificateList list = new CertificateList(service.doFilter(allRequestParams, page, elements));
        hateoas.createHateoas(list);
        return ResponseEntity.status(OK).body(list);
    }
}
