package org.ccs.app.core.academic.domain;

import jakarta.persistence.*;
import lombok.*;
import org.ccs.app.core.share.domain.BaseCreatedAndUpdatedDateTime;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ccs_academic_schedule")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Getter @ToString
@Builder
public class AcademicSchedule extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "academic_year")
    private Integer year;

    @Column(name = "closed")
    private Boolean closed; // 휴교 구분

    @Column(name = "reason")
    private String reason; // 왜 휴교했냐? 사유

    // TODO: 수업일자 정보 추가하기 (작성자: tjddnjs0102)
}
