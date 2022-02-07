package com.epam.spring.homework6.payments.service.mapper;

import com.epam.spring.homework6.payments.controller.dto.UserDto;
import com.epam.spring.homework6.payments.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapUserToUserDto(User user);
    User mapUserDtoToUser(UserDto userDto);
    List<UserDto> mapUserToUserDtos(List<User> user);

}
