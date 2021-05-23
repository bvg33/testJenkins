package com.epam.ems.dto;

import com.epam.ems.audit.AuditListener;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = "id")
@EntityListeners(AuditListener.class)
@Entity
@Table(name = "epam.gift_certificate")
public class Certificate extends RepresentationModel<Certificate> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id")
    @Min(1)
    private int id;
    @Pattern(regexp = "^.{0,45}$")
    private String name;
    @Pattern(regexp = "^.{0,45}$")
    private String description;
    @Min(0)
    private int price;
    @Min(0)
    private int duration;
    @Column(name = "create_date")
    @Pattern(regexp = "^.{0,45}$")
    private String createDate;
    @Column(name = "last_update_date")
    @Pattern(regexp = "^.{0,45}$")
    private String lastUpdateDate;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "epam.certificate_tag",
            joinColumns = {@JoinColumn(name = "certificate_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<Tag> tags;

    public Certificate(String name, String description, int price, int duration, String createDate, String lastUpdateDate, List<Tag> tags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.tags = new ArrayList<>(tags);
    }
}
