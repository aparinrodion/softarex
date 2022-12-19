package com.softarex.portal.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FieldDto {
    private Long id;
    private String label;
    private String type;
    private Boolean isRequired;
    private Boolean isActive;
    private Long questionnaireId;
    private List<OptionDto> options = new ArrayList<>();
}
