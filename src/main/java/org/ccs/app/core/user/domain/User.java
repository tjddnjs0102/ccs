package org.ccs.app.core.user.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ccs.app.config.Audit;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Table(name = "ccs_user")
@DynamicInsert
@DynamicUpdate
@Getter @ToString
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private UserType type;
    private String name;
    private Integer parish; // 교구
    private Integer cellGroup; //구역
    private FellowShipGroup fellowShipGroup; // 회별
    private Gender gender;
    private String zipcode; // 우편
    private String address;
    private String phoneNumber;
    private String profileImage;

    private Boolean state;
    private String reason;

    @Embedded
    private Audit audit;

}
