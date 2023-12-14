package org.ccs.app.core.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ccs.app.config.Audit;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ccs_user_account")
@Getter
@Setter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // 이메일 주소가 고유함
    private String email; // 로그인 ID로 사용

    @Column
    private String password;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserRole> roles;

    @Column
    private Integer loginFailureCount;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    // 유저 로그인에만 해당하는 특별한 정보라 생각하여 Audit으로 구분하지 않음
    @Column
    private LocalDateTime lastAccessAt;

    @Column
    private LocalDateTime passwordChangedAt;

    @Embedded
    private Audit audit;

}
