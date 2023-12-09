package org.ccs.app.entrypoints.academic.model;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @ToString
public class CreateAcademicScheduleRequest {

    @Min(value = 2020L)
    private Integer targetDate;
}
