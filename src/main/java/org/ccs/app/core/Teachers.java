package org.ccs.app.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity // 데이터베이스의 테이블과 매핑
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private Integer age;
//    private String zone;
    private String gender;

    public Teachers(String name, Integer age,String gender) {
        this.name = name;
        this.age = age;
//        this.zone = zone;
        this.gender = gender;
    }

    public Teachers() {

    }
}
