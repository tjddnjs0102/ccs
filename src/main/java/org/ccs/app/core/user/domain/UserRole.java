package org.ccs.app.core.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ccs.app.config.Audit;

import java.time.LocalDateTime;

@Entity
@Table(name = "ccs_user_roles")
@Getter
@Setter
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ccs_account_id")
    private UserAccount account;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Audit audit;
}
