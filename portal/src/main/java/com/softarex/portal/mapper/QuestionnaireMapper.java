package com.softarex.portal.mapper;

import com.softarex.portal.dto.QuestionnaireDto;
import com.softarex.portal.model.Questionnaire;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionnaireMapper {
    Questionnaire mapToEntity(QuestionnaireDto questionnaireDto);

    QuestionnaireDto mapToDto(Questionnaire questionnaire);
}
