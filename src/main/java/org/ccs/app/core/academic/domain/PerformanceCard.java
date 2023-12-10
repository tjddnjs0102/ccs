package org.ccs.app.core.academic.domain;

import org.ccs.app.core.studunt.domain.Student;

import java.time.LocalDateTime;

public class PerformanceCard {
    private Long id;
    // private Long academicScheduleId; // trade off
    private String date; // yyyyMMdd
    private Student student;
    // TODO: study..
//    @AttributeOverrides({
//            @AttributeOverride(name = "company", column = @Column(name = "sns_company")),
//            @AttributeOverride(name = "address", column = @Column(name = "sns_address")),
//    })
    private PerformancePoints points;
    private LocalDateTime createdAt;
}
