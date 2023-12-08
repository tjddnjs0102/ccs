package org.ccs.app.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Students {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Integer age;
  private String zone; // 소속구역
  private String gender;

  public Students(String name, Integer age, String zone, String gender) {
    this.name = name;
    this.age = age;
    this.zone = zone;
    this.gender = gender;
  }

  public Students() {

  }
}
