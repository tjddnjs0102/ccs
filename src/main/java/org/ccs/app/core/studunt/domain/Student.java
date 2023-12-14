package org.ccs.app.core.studunt.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ccs.app.config.Audit;
import org.ccs.app.core.user.domain.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "ccs_student")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ccs_user_id")
    private User student;

    @Embedded
    private Audit audit;
}
