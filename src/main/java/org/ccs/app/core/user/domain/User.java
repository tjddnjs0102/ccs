package org.ccs.app.core.user.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ccs.app.core.share.domain.BaseCreatedAndUpdatedDateTime;
import org.ccs.app.core.user.domain.converter.FellowShipGroupToStringConverter;
import org.ccs.app.core.user.domain.converter.GenderToStringConverter;
import org.ccs.app.core.user.domain.converter.UserTypeToStringConverter;
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
public class User extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Convert(converter = UserTypeToStringConverter.class)
    @Column(name = "type")
    private UserType type;

    @Column(name = "name")
    private String name;

    @Column(name = "parish")
    private Integer parish; // 교구

    @Column(name = "cell_group")
    private Integer cellGroup; //구역

    @Convert(converter = FellowShipGroupToStringConverter.class)
    @Column(name = "fellowship_group")
    private FellowShipGroup fellowShipGroup; // 회별

    @Convert(converter = GenderToStringConverter.class)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "zipcode")
    private String zipcode; // 우편

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profile_image")
    private String profileImage;


    @Column(name = "state")
    private Boolean state;

    @Column(name = "reason")
    private String reason;
}

