package com.softarex.portal.mapper;

import com.softarex.portal.dto.RegistrationDto;
import com.softarex.portal.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    User mapToEntity(RegistrationDto registrationDto);
}
