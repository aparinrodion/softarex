package com.softarex.portal.mapper;

import com.softarex.portal.dto.UserDto;
import com.softarex.portal.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    UserDto mapToDto(User user);
}
