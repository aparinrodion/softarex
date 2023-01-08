package com.softarex.portal.dto;

import lombok.Data;

@Data
public class FieldResponseDto {
    private Long id;
    private Long fieldId;
    private Long responseId;
    private String answer;
}
