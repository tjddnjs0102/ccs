package org.ccs.app.core.academic.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ccs.app.core.share.domain.BaseCreatedAndUpdatedDateTime;
import org.ccs.app.core.studunt.domain.Student;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "ccs_performance_card")
@DynamicInsert @DynamicUpdate
@NoArgsConstructor @AllArgsConstructor
@Getter @ToString
public class PerformanceCard extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//     private Long academicScheduleId; // trade off

    @Column(name = "date")
    private String date; // yyyyMMdd

//    @ManyToOne
//    @JoinColumn(name = "ccs_student_id")
//    private Student student;

//    아직 student 테이블이 없기 때문에 임시 필드 사용하기
    @Column(name = "ccs_student_id")
    private Long studentId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "attendance", column = @Column(name = "attendance")),
            @AttributeOverride(name = "homework", column = @Column(name = "homework")),
            @AttributeOverride(name = "weeklyVerse", column = @Column(name = "weekly_verse")),
            @AttributeOverride(name = "monthlyVerse", column = @Column(name = "monthly_verse"))
    })
    private PerformancePoints points;
}
