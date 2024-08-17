package com.prueba.personaltraining.service.api;

import com.prueba.personaltraining.dto.UserDto;
import org.apache.catalina.User;

import java.util.List;

public interface BaseService {
    List<UserDto> getAllUsers();
}
