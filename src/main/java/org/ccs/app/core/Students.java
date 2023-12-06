package org.ccs.app.core;

import lombok.Data;

@Data
public class Students {
  private String name;
  private Integer age;
  private String zone; // 소속구역

  public Students(String name, Integer age, String zone) {
    this.name = name;
    this.age = age;
    this.zone = zone;
  }
}
