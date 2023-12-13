package org.ccs.app.core.studunt.domain;

import jakarta.persistence.Embedded;
import org.ccs.app.config.Audit;
import org.ccs.app.core.user.domain.User;

import java.time.LocalDateTime;

public class Student {
    private Long id;
    private User student;

    @Embedded
    private Audit audit;
}
