package org.ccs.app.core.academic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ccs.app.config.Audit;
import org.ccs.app.core.studunt.domain.Student;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "ccs_section")
@Getter
@Setter
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Division division;

    private Integer grade;
    private Integer sectionNumber;

    @OneToMany(mappedBy = "section")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "section")
    private List<Student> students;

    @Embedded
    private Audit audit;
}
