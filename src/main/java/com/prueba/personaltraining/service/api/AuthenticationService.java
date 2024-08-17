package com.prueba.personaltraining.service.api;

import com.prueba.personaltraining.dto.AuthResponseDto;
import com.prueba.personaltraining.dto.LoginRequestDto;

public interface AuthenticationService {

    AuthResponseDto login(LoginRequestDto loginRequestDto);

    AuthResponseDto register(LoginRequestDto loginRequestDto);
}
