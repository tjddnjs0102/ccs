package org.ccs.app.core;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    // 일요일 목록을 저장할 리스트
    private List<LocalDate> sundayDates = new ArrayList<>();

    @Override
    public List<LocalDate> getSundays(int year) {
        // 기존의 일요일 목록을 초기화
        sundayDates.clear();

        // 주어진 연도의 시작일과 종료일 설정
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        // 시작일부터 종료일까지 반복하면서 일요일인 경우 리스트에 추가
        while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
            if (startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                sundayDates.add(startDate);
            }
            startDate = startDate.plusDays(1);
        }

        // 생성된 일요일 목록 반환
        return sundayDates;
    }

    @Override
    public void addSchedule(LocalDate date) {
        // 주어진 날짜를 일요일 목록에 추가
        sundayDates.add(date);
    }

    @Override
    public void deleteSchedule(int index) {
        // 주어진 인덱스에 해당하는 일요일을 삭제 (인덱스가 유효한 범위 내에 있을 때)
        if (index >= 0 && index < sundayDates.size()) {
            sundayDates.remove(index);
        }
    }

    @Override
    public void updateSchedule(int index, LocalDate date) {
        // 주어진 인덱스에 해당하는 일요일을 주어진 날짜로 업데이트 (인덱스가 유효한 범위 내에 있을 때)
        if (index >= 0 && index < sundayDates.size()) {
            sundayDates.set(index, date);
        }
    }
}