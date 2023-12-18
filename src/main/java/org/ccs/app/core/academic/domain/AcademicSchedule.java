package org.ccs.app.core.academic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ccs_academic_schedule")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class AcademicSchedule {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "closed")
    private Boolean closed; // 휴교 구분

    @Column(name = "reason")
    private String reason; // 왜 휴교했냐? 사유

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated at")
    private LocalDateTime updatedAt;
}
