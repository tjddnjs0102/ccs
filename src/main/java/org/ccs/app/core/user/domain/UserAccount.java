package org.ccs.app.core.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ccs.app.core.share.domain.BaseCreatedAndUpdatedDateTime;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "ccs_user_account")
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@Getter @ToString
public class UserAccount extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email; // login id

    @Column(name = "password")
    private String password; // login pw

    @OneToMany(mappedBy = "account")
    private List<UserRole> roles;

    @Column(name = "login_failure_count")
    private Integer loginFailureCount;

    @Column(name = "status")
    private AccountStatus status;

    @Column(name = "last_access_dt")
    private LocalDateTime lastAccessAt;

    @Column(name = "password_changed_dt")
    private LocalDateTime passwordChangedAt;
}
