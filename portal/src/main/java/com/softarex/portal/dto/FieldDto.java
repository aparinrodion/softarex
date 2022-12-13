package com.softarex.portal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softarex.portal.model.Option;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class FieldDto {
    private Long id;
    @NotBlank
    private String label;
    @NotBlank
    private String type;
    @NotNull
    private Boolean isRequired;
    @NotNull
    private Boolean isActive;
    @NotNull
    private Long questionnaireId;
    private List<OptionDto> options = new ArrayList<>();
}
