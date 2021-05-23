package com.epam.ems.dto;

import com.epam.ems.audit.AuditListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = "id")
@EntityListeners(AuditListener.class)
@Entity
@Table(name = "epam.users")
public class AppUser extends RepresentationModel<AppUser> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Min(0)
    private int id;
    @Pattern(regexp = "^.{0,60}$")
    @NonNull
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    @NonNull
    private Role role;
    @Pattern(regexp = "^.{0,45}$")
    @NonNull
    private String nickname;
    @Min(0)
    @NonNull
    private int money;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "epam.users_certificates",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "certificate_id")}
    )
    @NonNull
    private List<Certificate> certificates;
    @Column(name = "overage_order_price")
    @Min(0)
    @NonNull
    private int overageOrderPrice;
}
