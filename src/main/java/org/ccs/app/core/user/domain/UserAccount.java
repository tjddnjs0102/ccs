package org.ccs.app.core.user.domain;

import jakarta.persistence.Embedded;
import org.ccs.app.config.Audit;

import java.time.LocalDateTime;
import java.util.List;

public class UserAccount {
    private Long id;
    private String email; // login id
    private String password; //
    private List<UserRole> roles;
    private Integer loginFailureCount;
    private AccountStatus status;

    // 유저 로그인에만 해당하는 특별한 정보라 생각하여 Audit으로 구분하지 않음
    private LocalDateTime lastAccessAt;
    private LocalDateTime passwordChangedAt;

    @Embedded
    private Audit audit;

}
