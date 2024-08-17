package com.prueba.personaltraining.service.impl;

import com.prueba.personaltraining.dto.AuthResponseDto;
import com.prueba.personaltraining.dto.LoginRequestDto;
import com.prueba.personaltraining.model.UserEntity;
import com.prueba.personaltraining.repository.RoleRepository;
import com.prueba.personaltraining.repository.UserRepository;
import com.prueba.personaltraining.service.api.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    UserRepository userRepository;

    JWTServiceImpl jwtService;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, JWTServiceImpl jwtService, AuthenticationManager authenticationManager, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {

        AuthResponseDto authResponseDto =new AuthResponseDto();

        authResponseDto.setSuccess(false);
        authResponseDto.setMessage("Credenciales inválidas, vuelve a intentarlo");

        String token = generateToken(loginRequestDto.getDni(), loginRequestDto.getPassword());

        if(token != null) {
            authResponseDto.setSuccess(true);
            authResponseDto.setMessage("success");
            authResponseDto.setToken(token);
        }

        return authResponseDto;
    }

    @Override
    public AuthResponseDto register(LoginRequestDto loginRequestDto) {

        AuthResponseDto authResponseDto =new AuthResponseDto();

        authResponseDto.setSuccess(false);
        authResponseDto.setMessage("error_total");

        if(!validateInput(loginRequestDto)) {
            authResponseDto.setMessage("Introduce el usuario y la contraseña");
            return authResponseDto;
        }

        if(checkUserExist(loginRequestDto.getDni())) {
            authResponseDto.setMessage("Este usuario ya existe, inicia sesión");
            return authResponseDto;
        }

        UserEntity newUser = new UserEntity();
        newUser.setDni(loginRequestDto.getDni());
        newUser.setPassword(loginRequestDto.getPassword());
        newUser.setRole(roleRepository.findById(2).get());
        newUser.setName(loginRequestDto.getDni());
        newUser.setCreateTime(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        newUser.setStatus("Active");

        userRepository.save(newUser);

        String token = generateToken(loginRequestDto.getDni(), loginRequestDto.getPassword());

        if(token != null) {
            authResponseDto.setSuccess(true);
            authResponseDto.setMessage("success");
            authResponseDto.setToken(token);
        }

        return authResponseDto;

    }

    private String generateToken(String dni, String password) {

        UserEntity user = userRepository.findByDni(dni);

        String hashedPass = DigestUtils
                .md5Hex(password);

        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(hashedPass)) {
            return null;
        }

        return jwtService.createToken(user);
    }

    private boolean checkUserExist(String dni) {
        UserEntity user = userRepository.findByDni(dni);

        if (user == null) {
            return false;
        }
        return true;
    }

    private boolean validateInput (LoginRequestDto loginRequestDto) {
       return loginRequestDto.getDni() != null && loginRequestDto.getPassword() != null;
    }
}
