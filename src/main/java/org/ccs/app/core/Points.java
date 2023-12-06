package org.ccs.app.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 달란트 포인트
public class Points {
    // Collections.synchronizedMap - 여러 스레드가 동시에 맵을 수정할 때 발생할 수 있는 문제를 방지
    // HashMap - 날짜별 데이터를 효과적으로 관리하고, 검색 및 업데이트의 성능을 보장
    private final Map<String, Integer> attendancePoints = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Integer> homeworkPoints = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Integer> weeklyRecitationPoints = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Integer> monthlyRecitationPoints = Collections.synchronizedMap(new HashMap<>());
    private final Map<String, Integer> totalPoints = Collections.synchronizedMap(new HashMap<>());

    // 출석 포인트 추가
    public void addAttendancePoints(String date, int points) {
        attendancePoints.put(date, points);
        updateTotalPoints(date, points);
    }

    // 머릿돌 포인트 추가
    public void addHomeworkPoints(String date, int points) {
        homeworkPoints.put(date, points);
        updateTotalPoints(date, points);
    }

    // 주암송 포인트 추가
    public void addSundayRecitationPoints(String date, int points) {
        weeklyRecitationPoints.put(date, points);
        updateTotalPoints(date, points);
    }

    // 월암송 포인트 추가
    public void addMonthlyRecitationPoints(String date, int points) {
        monthlyRecitationPoints.put(date, points);
        updateTotalPoints(date, points);
    }

    private void updateTotalPoints(String date, int points) {
        int attendance = attendancePoints.getOrDefault(date, 0);
        int weeklyRecitation = weeklyRecitationPoints.getOrDefault(date, 0);
        int monthlyRecitation = monthlyRecitationPoints.getOrDefault(date, 0);
        int homework = homeworkPoints.getOrDefault(date, 0);

        // 주암송 + 월암송 합계
        int totalRecitation = weeklyRecitation + monthlyRecitation;

        int total = attendance + totalRecitation + homework;

        totalPoints.put(date, total);
    }
}
