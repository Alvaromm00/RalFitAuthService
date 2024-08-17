package com.prueba.personaltraining.service.impl;

import com.prueba.personaltraining.dto.UserDto;
import com.prueba.personaltraining.model.UserEntity;
import com.prueba.personaltraining.repository.UserRepository;
import com.prueba.personaltraining.service.api.BaseService;
import com.prueba.personaltraining.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseServiceImpl implements BaseService{

    UserRepository userRepository;
    UserMapper userMapper;

    @Autowired
    public BaseServiceImpl(UserRepository userRepository,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<UserEntity> users = userRepository.findAll();
        return userMapper.userEntityListToUserDtoList(users);

    }
}
