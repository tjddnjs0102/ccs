package org.ccs.app.core.academic.application;

import org.ccs.app.core.academic.application.usecase.GenerateAcademicScheduleUsecase;
import org.ccs.app.core.academic.domain.AcademicSchedule;
import org.ccs.app.core.user.infra.repository.AcademicScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AcademicScheduleApplication implements GenerateAcademicScheduleUsecase {
    private final Logger log = LoggerFactory.getLogger(AcademicScheduleApplication.class);

    @Autowired
    private AcademicScheduleRepository scheduleRepository;

    @Override
    public List<LocalDate> generate(Integer targetYear) {
        LocalDate start = LocalDate.of(targetYear, 1, 1);
        LocalDate end = LocalDate.of(targetYear, 12, 31);

        List<LocalDate> schedules = new ArrayList<>();
        while (start.isBefore(end) || start.isEqual(end)) {
            if (start.getDayOfWeek() == DayOfWeek.SUNDAY) {
                schedules.add(start);
            }
            start = start.plusDays(1);
        }

        // TODO : [V] repository 연결 후 저장하는 로직을 구현하세요.
        // for-each → 각 날짜에 대해 동일한 작업 수행
        for (LocalDate scheduleDate : schedules) {
            AcademicSchedule academicSchedule = new AcademicSchedule();
            academicSchedule.setYear(targetYear);
            academicSchedule.setClosed(false);
            academicSchedule.setReason("휴교 사유");
            academicSchedule.getAudit().setUpdatedAt(LocalDateTime.now());

            try {
                academicSchedule = scheduleRepository.save(academicSchedule);
                log.info("학사 일정이 저장되었습니다. ID: {}", academicSchedule.getId());
            } catch (Exception e) {
                log.error("날짜 {}에 대한 학사 일정 저장 실패", scheduleDate, e);
                // throw new CustomException("학사 일정 저장에 실패했습니다.", e);
            }

        }

            return schedules;

    }
}