package org.ccs.app.core.academic.domain;

import org.ccs.app.core.studunt.domain.Student;

import java.time.LocalDateTime;

public class PerformanceCard {
    private Long id;
    private Long academicScheduleId;
    private Student student;
    private PerformancePoints points;
    private LocalDateTime createdAt;
}
