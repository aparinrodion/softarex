package com.softarex.portal.mapper;

import com.softarex.portal.dto.FieldDto;
import com.softarex.portal.model.Field;
import com.softarex.portal.util.enums.Type;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        uses = {OptionMapper.class})
public abstract class FieldMapper {
    @Mapping(target = "type", source = "type", qualifiedByName = "getTypeByInfo")
    public abstract Field mapToEntity(FieldDto fieldDto);

    @Mapping(target = "type", source = "type.info")
    public abstract FieldDto mapToDto(Field field);

    @Named("getTypeByInfo")
    static Type getTypeByInfo(String type) {
        return Type.get(type);
    }
}
