package org.ccs.app.core.academic.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity가 아니라 VO입니다.
 */
@Getter
@Setter
public class PerformancePoints {
    private Integer attendance;
    private Integer homework;
    private Integer weeklyVerse;
    private Integer monthlyVerse;
}
