package com.softarex.portal.mapper;

import com.softarex.portal.dto.OptionDto;
import com.softarex.portal.model.Option;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionMapper {
    Option mapToEntity(OptionDto optionDto);

    @Mapping(target = "fieldId", source = "option.field.id")
    OptionDto mapToDto(Option option);
}
