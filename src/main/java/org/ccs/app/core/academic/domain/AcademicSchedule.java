package org.ccs.app.core.academic.domain;

import java.time.LocalDateTime;

public class AcademicSchedule {
    private Long id;
    private Integer year;
    private Boolean closed; // 휴교 구분
    private String reason; // 왜 휴교했냐? 사유
    private LocalDateTime updatedAt;
}
