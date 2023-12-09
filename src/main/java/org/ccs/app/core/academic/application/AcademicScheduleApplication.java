package org.ccs.app.core.academic.application;

import org.ccs.app.core.academic.application.usecase.GenerateAcademicScheduleUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AcademicScheduleApplication implements GenerateAcademicScheduleUsecase {
    private final Logger log = LoggerFactory.getLogger(AcademicScheduleApplication.class);

    @Override
    public List<LocalDate>  generate(Integer targetYear) {
        LocalDate start = LocalDate.of(targetYear, 1, 1);
        LocalDate end = LocalDate.of(targetYear, 12, 31);

        List<LocalDate> schedules = new ArrayList<>();
        while (start.isBefore(end) || start.isEqual(end)) {
            if (start.getDayOfWeek() == DayOfWeek.SUNDAY) {
                schedules.add(start);
            }
            start = start.plusDays(1);
        }

        // TODO : repository 연결 후 저장하는 로직을 구현하세요.

        return schedules;
    }
}