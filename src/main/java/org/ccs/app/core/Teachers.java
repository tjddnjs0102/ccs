package org.ccs.app.core;

import lombok.Data;

@Data
public class Teachers {
    private String name;
    private Integer age;
    private String zone;

    public Teachers(String name, Integer age, String zone) {
        this.name = name;
        this.age = age;
        this.zone = zone;
    }
}
