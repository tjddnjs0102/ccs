package org.ccs.app.core.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.ccs.app.core.share.authenticate.exception.IncorrectPasswordException;
import org.ccs.app.core.share.domain.BaseCreatedAndUpdatedDateTime;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ccs_user_account")
@DynamicUpdate
@DynamicInsert
@Getter
@ToString(exclude = "roles")
public class UserAccount extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email; // login id

    @Column(name = "password")
    private String password; // login pw

    @Column(name = "login_failure_count")
    private Integer loginFailureCount;

    @Column(name = "status")
    private AccountStatus status;

    @Column(name = "last_access_dt")
    private LocalDateTime lastAccessAt;

    @Column(name = "password_changed_dt")
    private LocalDateTime passwordChangedAt;

    @OneToMany(mappedBy = "account")
    private List<UserRole> roles;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void confirmPassword(String password) {
        if (!Objects.equals(this.password, password)) {
            // login failure
            loginFailureCount = loginFailureCount + 1;
            if (loginFailureCount > 5) {
                this.status = AccountStatus.LOCKED;
            }
            throw new IncorrectPasswordException();
        }

        this.lastAccessAt = LocalDateTime.now();
        this.loginFailureCount = 0;
    }
}
