package com.epam.ems.dto;


import com.epam.ems.audit.AuditListener;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@EntityListeners(AuditListener.class)
@Entity
@Table(name = "epam.tag")
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "tagName")
@NoArgsConstructor
@RequiredArgsConstructor
public class Tag extends RepresentationModel<Tag> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int id;
    @Column(name = "tag_name")
    @Pattern(regexp = "^.{0,45}$")
    @NonNull
    private String tagName;
}
