package org.ccs.app.core.academic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ccs.app.config.Audit;
import org.ccs.app.core.studunt.domain.Student;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "ccs_performance_card")
@Getter
@Setter
public class PerformanceCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // private Long academicScheduleId; // trade off

    private String date; // yyyyMMdd

    @ManyToOne
    @JoinColumn(name = "ccs_student_id")
    private Student student;


    // TODO: study..
//    @AttributeOverrides({
//            @AttributeOverride(name = "company", column = @Column(name = "sns_company")),
//            @AttributeOverride(name = "address", column = @Column(name = "sns_address")),
//    })

    // @AttributeOverrides를 사용하여 임베디드 클래스의 각 필드가 매핑될 컬럼 이름을 지정
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "attendance", column = @Column(name = "ccs_attendance_points")),
            @AttributeOverride(name = "homework", column = @Column(name = "ccs_homework_points")),
            @AttributeOverride(name = "weeklyVerse", column = @Column(name = "ccs_weekly_verse_points")),
            @AttributeOverride(name = "monthlyVerse", column = @Column(name = "ccs_monthly_verse_points"))
    })
    private PerformancePoints points;

    @Embedded
    private Audit audit;
}
