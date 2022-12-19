package com.softarex.portal.mapper;

import com.softarex.portal.dto.FieldResponseDto;
import com.softarex.portal.model.FieldResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldResponseMapper {
    FieldResponse mapToEntity(FieldResponseDto fieldResponseDto);

    @Mapping(target = "responseId", source = "response.id")
    FieldResponseDto mapToDto(FieldResponse fieldResponse);
}
