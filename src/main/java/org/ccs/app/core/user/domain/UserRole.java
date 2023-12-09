package org.ccs.app.core.user.domain;

import java.time.LocalDateTime;

public class UserRole {
    private Long id;
    private UserAccount account;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
