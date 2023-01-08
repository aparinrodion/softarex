package com.softarex.portal.mapper;

import com.softarex.portal.dto.UserDto;
import com.softarex.portal.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapToDto(User user);

    User mapToEntity(UserDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(User userToUpdate, @MappingTarget User user);
}
