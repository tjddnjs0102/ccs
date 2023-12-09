package org.ccs.app.core.studunt.domain;

import org.ccs.app.core.user.domain.User;

import java.time.LocalDateTime;

public class Student {
    private Long id;
    private User student;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
