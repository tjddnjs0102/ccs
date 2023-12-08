package org.ccs.app.core;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    List<LocalDate> getSundays(int year);
    void addSchedule(LocalDate date);
    void deleteSchedule(int index);
    void updateSchedule(int index, LocalDate date);
}
