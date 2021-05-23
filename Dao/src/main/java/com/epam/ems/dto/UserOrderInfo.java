package com.epam.ems.dto;

import com.epam.ems.audit.AuditListener;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditListener.class)
@Entity
@Table(name = "epam.users_certificates")
public class UserOrderInfo extends RepresentationModel {
    @Id
    private int id;

    @Column(name = "user_id")
    @NonNull
    private int userId;
    @Column(name = "certificate_id")
    @NonNull
    private int certificateId;
    @Column(name = "certificate_price")
    @NonNull
    private int certificatePrice;
    @Column(name = "buy_date")
    @NonNull
    private String date;
}
