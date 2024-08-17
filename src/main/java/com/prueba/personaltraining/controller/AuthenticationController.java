package com.prueba.personaltraining.controller;

import com.prueba.personaltraining.dto.AuthResponseDto;
import com.prueba.personaltraining.dto.LoginRequestDto;
import com.prueba.personaltraining.service.api.AuthenticationService;
import com.prueba.personaltraining.service.impl.AuthenticationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private static final Logger LOG = LogManager.getLogger(AuthenticationController.class);

    @Autowired
    AuthenticationService authenticationService;

    @CrossOrigin(origins = "*")
    @PostMapping(value="/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LOG.debug(LocalDate.now());
        LOG.debug("\"2024-07-26 12:00:00,000 [main] INFO = Inicio de sesion en /login con credenciales " + loginRequestDto);
        return ResponseEntity.ok(authenticationService.login(loginRequestDto));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value="/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authenticationService.register(loginRequestDto));
    }

}
