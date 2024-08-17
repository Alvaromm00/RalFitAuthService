package com.prueba.personaltraining.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String dni;
    private String password;
}
