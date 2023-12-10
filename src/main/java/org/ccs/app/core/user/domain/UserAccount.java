package org.ccs.app.core.user.domain;

import java.time.LocalDateTime;
import java.util.List;

public class UserAccount {
    private Long id;
    private String email; // login id
    private String password; //
    private List<UserRole> roles;
    private Integer loginFailureCount;
    private AccountStatus status;
    private LocalDateTime lastAccessAt;
    private LocalDateTime passwordChangedAt;
    private LocalDateTime updatedAt;
}
