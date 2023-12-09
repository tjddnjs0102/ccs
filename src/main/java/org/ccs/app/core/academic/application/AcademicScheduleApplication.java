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

    // 일요일 목록을 저장할 리스트
    private List<LocalDate> sundayDates = new ArrayList<>();

    @Override
    public void generate(Integer targetYear) {
        // 기존의 일요일 목록을 초기화
        sundayDates.clear();

        // 주어진 연도의 시작일과 종료일 설정
        LocalDate startDate = LocalDate.of(targetYear, 1, 1);
        LocalDate endDate = LocalDate.of(targetYear, 12, 31);

        // 시작일부터 종료일까지 반복하면서 일요일인 경우 리스트에 추가
        while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
            if (startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                sundayDates.add(startDate);
            }
            startDate = startDate.plusDays(1);
        }

        // 생성된 일요일 목록 반환
        // return sundayDates;
    }
}