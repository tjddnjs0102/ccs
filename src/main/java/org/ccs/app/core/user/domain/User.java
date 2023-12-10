package org.ccs.app.core.user.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Table(name = "css_user")
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
    private String zipcode;
    private String address;
    private String phoneNumber;
    private String profileImage;

    private Boolean state;
    private String reason;

    // fixme : Audit으로 분리할 것
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
