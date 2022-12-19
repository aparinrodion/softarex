package com.softarex.portal.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {
    private Long id;
    private Long questionnaireId;
    private List<FieldResponseDto> fieldResponses;
}
