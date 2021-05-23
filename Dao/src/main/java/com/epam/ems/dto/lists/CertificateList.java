package com.epam.ems.dto.lists;

import com.epam.ems.dto.Certificate;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
public class CertificateList extends RepresentationModel<CertificateList> {
    private List<Certificate> certificates;
}
