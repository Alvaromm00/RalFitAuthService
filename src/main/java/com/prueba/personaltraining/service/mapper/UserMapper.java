package com.prueba.personaltraining.service.mapper;

import com.prueba.personaltraining.dto.UserDto;
import com.prueba.personaltraining.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userEntityToUserDto(UserEntity userEntity);

    List<UserDto> userEntityListToUserDtoList(List<UserEntity> userEntities);
}
