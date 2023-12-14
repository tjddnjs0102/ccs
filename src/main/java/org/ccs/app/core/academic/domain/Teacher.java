package org.ccs.app.core.academic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ccs.app.config.Audit;
import org.ccs.app.core.user.domain.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "ccs_teacher")
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ccs_user_id")
    private User teacher;

    @Column
    private Boolean head; // 정•부 구분

    @Embedded
    private Audit audit;
}
