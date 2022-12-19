package com.softarex.portal.mapper;

import com.softarex.portal.dto.FieldDto;
import com.softarex.portal.model.Field;
import com.softarex.portal.util.enums.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",
        uses = {OptionMapper.class})
public interface FieldMapper {
    @Mapping(target = "type", source = "type", qualifiedByName = "getTypeByInfo")
    Field mapToEntity(FieldDto fieldDto);

    @Mapping(target = "type", source = "type.info")
    FieldDto mapToDto(Field field);

    @Named("getTypeByInfo")
    static Type getTypeByInfo(String type) {
        return Type.get(type);
    }
}
