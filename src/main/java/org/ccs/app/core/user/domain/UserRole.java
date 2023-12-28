package org.ccs.app.core.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ccs.app.core.share.domain.BaseCreatedAndUpdatedDateTime;
import org.ccs.app.core.user.domain.converter.RoleToStringConverter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@NoArgsConstructor
@Entity
@Table(name = "ccs_user_role")
@DynamicInsert
@DynamicUpdate
@Getter @ToString
public class UserRole extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    @Convert(converter = RoleToStringConverter.class)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "ccs_user_account_id")
    private UserAccount account;
}
