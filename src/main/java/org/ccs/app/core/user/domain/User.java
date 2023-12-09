package org.ccs.app.core.user.domain;


import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

// Lombok에 @Data는 사용을 지향합니다.
@Getter @ToString
public class User {
    private Long id;
    private UserType type;
    private String name;
    private Integer parish; // 교구
    private Integer cellGroup; //구역
    private FellowShipGroup fellowShipGroup; // 회별
    private Gender gender;
    private Address address;
    private String phoneNumber;
    private String profileImage;

    private Boolean state;
    private String reason;

    // fixme : Audit으로 분리할 것
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
