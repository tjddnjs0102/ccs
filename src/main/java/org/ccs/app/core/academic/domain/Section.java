package org.ccs.app.core.academic.domain;

import jakarta.persistence.Embedded;
import org.ccs.app.config.Audit;
import org.ccs.app.core.studunt.domain.Student;

import java.time.LocalDateTime;
import java.util.List;
// 분반
public class Section {
    private Long id;
    private Integer year;
    private Department department;
    private Division division;
    private Integer grade;
    private Integer sectionNumber;

    private List<Teacher> teachers;
    private List<Student> students;

    @Embedded
    private Audit audit;
}
