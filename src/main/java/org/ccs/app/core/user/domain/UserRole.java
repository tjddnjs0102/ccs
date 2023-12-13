package org.ccs.app.core.user.domain;

import jakarta.persistence.Embedded;
import org.ccs.app.config.Audit;

import java.time.LocalDateTime;

public class UserRole {
    private Long id;
    private UserAccount account;
    private Role role;

    @Embedded
    private Audit audit;
}
