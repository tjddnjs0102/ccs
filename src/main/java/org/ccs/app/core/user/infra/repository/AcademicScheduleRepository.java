package org.ccs.app.core.user.infra.repository;

import org.ccs.app.core.academic.domain.AcademicSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicScheduleRepository extends JpaRepository<AcademicSchedule, Long> {
}
