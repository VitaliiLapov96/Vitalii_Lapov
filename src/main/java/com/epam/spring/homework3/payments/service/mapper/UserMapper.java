package com.epam.spring.homework3.payments.service.mapper;

import com.epam.spring.homework3.payments.controller.dto.UserDto;
import com.epam.spring.homework3.payments.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

}
