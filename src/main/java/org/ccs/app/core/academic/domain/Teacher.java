package org.ccs.app.core.academic.domain;

import jakarta.persistence.Embedded;
import org.ccs.app.config.Audit;
import org.ccs.app.core.user.domain.User;

import java.time.LocalDateTime;

public class Teacher {
    private Long id;
    private User teacher;
    private Boolean head; // 정,부 구분

    @Embedded
    private Audit audit;
}
