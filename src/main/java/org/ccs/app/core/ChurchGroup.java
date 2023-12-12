package org.ccs.app.core;

import lombok.Getter;

@Getter
public enum ChurchGroup {
    ELEMENTARY_SCHOOL("유초등부"),
    MIDDLE_HIGH_SCHOOL("중고등부"),
    YOUTH_GROUP("청년회"),
    VOLUNTEER_GROUP("봉사회"),
    MOTHERS_GROUP("어머니회"),
    Senior_GROUP("은장회");

    private final String koreanName;

    ChurchGroup(String koreanName) {
        this.koreanName = koreanName;
    }

}
