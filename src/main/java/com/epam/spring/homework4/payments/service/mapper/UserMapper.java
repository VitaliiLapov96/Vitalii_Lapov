package com.epam.spring.homework4.payments.service.mapper;

import com.epam.spring.homework4.payments.controller.dto.UserDto;
import com.epam.spring.homework4.payments.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

}
