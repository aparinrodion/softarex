package com.softarex.portal.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.Date;

@Data
public class QuestionnaireDto {
    @Null
    private Long id;
    @NotBlank
    private String name;
    @Null
    private LocalDate creationDate;
}
