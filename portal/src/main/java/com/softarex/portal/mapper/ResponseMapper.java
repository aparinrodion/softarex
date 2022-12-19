package com.softarex.portal.mapper;

import com.softarex.portal.dto.ResponseDto;
import com.softarex.portal.model.Questionnaire;
import com.softarex.portal.model.Response;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",
        uses = {FieldResponseMapper.class})
public interface ResponseMapper {
    @Mapping(target = "questionnaire", source = "questionnaireId", qualifiedByName = "getQuestionnaireById")
    Response mapToEntity(ResponseDto responseDto);

    @Mapping(target = "questionnaireId", source = "questionnaire.id")
    ResponseDto mapToDto(Response response);

    @Named("getQuestionnaireById")
    default Questionnaire getQuestionnaireById(Long questionnaireId) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(questionnaireId);
        return questionnaire;
    }
}
