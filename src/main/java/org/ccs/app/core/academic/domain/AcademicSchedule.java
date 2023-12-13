package org.ccs.app.core.academic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ccs.app.config.Audit;

import java.time.LocalDateTime;

@Entity
@Table(name = "ccs_academyschedule")
@Getter
@Setter
public class AcademicSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer year;
    private Boolean closed; // 휴교 구분
    private String reason; // 휴교 사유

    @Embedded
    private Audit audit;
}
